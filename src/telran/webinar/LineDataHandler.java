package telran.webinar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineDataHandler {
	private static final String FILE_NAME="lines.dat";
	
	public static void writeLines(List<Line> lines) throws FileNotFoundException, IOException {
		//1.Numbers of lines
		//Table of offsets(id_line, offset)
		try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME))){
			dos.writeInt(lines.size());
			long[] offsets = new long[lines.size()];
			// boolean - 1b
			//int - 4b
			//long - 8b
			//double 8b
			//float 4b
			//short - 2
			// 
			//n_lines (id_line + offset)
			long currentOffset = 4 + (4+8)*lines.size();
			int i=0;
			for(Line line:lines) {
				offsets[i] = currentOffset;
				dos.writeInt(line.getId());
				dos.writeLong(offsets[i]);
				currentOffset+=getLineSize(line);
				i++;
			}
			
			for(Line line:lines) {
				dos.writeInt(line.getId());
				dos.writeUTF(line.getName());
				dos.writeInt(line.getPoints().length);
				for(Point point:line.getPoints()) {
					dos.writeInt(point.getX());
					dos.writeInt(point.getY());
				}
			}
					
		}
	}

	public static List<Line> readLines() throws FileNotFoundException, IOException{
		List<Line> lines = new ArrayList<Line>();
		try(
				FileInputStream fin = new FileInputStream(FILE_NAME); 
				DataInputStream in = new DataInputStream(fin);
			){
			
			int numberOfLines = in.readInt();
			long currentOffset = (4+8)*numberOfLines;
			in.skipNBytes(currentOffset);
			
			long currPosition = fin.getChannel().position();
			System.out.println(currPosition);
			
			for (int i = 0; i < numberOfLines; i++) {
				int id = in.readInt();
				String name = in.readUTF();
				int n_points = in.readInt();
				Point[] points = new Point[n_points];
				for (int j = 0; j < n_points; j++) {
					int x = in.readInt();
					int y = in.readInt();
					Point point = new Point(x,y);
					points[j] = point;
				};
				
				Line line = new Line(id,name,points);
				lines.add(line);
			}
			
		}
		return lines;
	}
	
	public static Line readLineById(int id) throws FileNotFoundException, IOException {
		try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "r"))//w rw
		{
			
			int n_lines = raf.readInt();
			Map<Integer,Long> offsets = new HashMap<Integer, Long>();
			int idCur;
			long offsetCur;
			for(int i=0;i<n_lines;i++) {
				idCur = raf.readInt();
				offsetCur = raf.readLong();
				offsets.put(idCur,offsetCur);
			}
			
			Long offset = offsets.get(id);
			if(offset!=null)
			{
				raf.seek(offset);
				raf.readInt(); //id
				String name = raf.readUTF();
				int n_points = raf.readInt();
				Point[] points = new Point[n_points];
				int x,y;
				for (int i = 0; i < n_points; i++) {
					x=raf.readInt();
					y=raf.readInt();
					Point point = new Point(x, y);
					points[i] = point;
				}
				
				return new Line(id,name,points);
			}
			
		}
		return null;
	}
	
	public static Boolean rewriteCoordinatesLineById(int line_id,int  n_point,int  new_x,int  new_y) throws FileNotFoundException, IOException {
		try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "rw"))	//w rw
		{
			int n_lines = raf.readInt();
			Map<Integer,Long> offsets = new HashMap<Integer, Long>();
			int idCur;
			long offsetCur;
			for(int i=0;i<n_lines;i++) {
				idCur = raf.readInt();
				offsetCur = raf.readLong();
				offsets.put(idCur,offsetCur);
			}
			Long offset = offsets.get(line_id);
			if(offset!=null) {
				raf.seek(offset);
				raf.readInt(); //id
				String name = raf.readUTF();
				int n_points = raf.readInt();
				long curPos = raf.getChannel().position();
				raf.seek(curPos+n_point*(4+4));
				raf.writeInt(new_x);
				raf.writeInt(new_y);
				return true;
			}
		}
		return false;
	}
	
	private static long getLineSize(Line line) {
		// id utf(count of symbols) char*длину имени число точек; количество точек по int 
		return 4 + 2 + line.getName().length() + 4 + line.getPoints().length*4 + line.getPoints().length*4;
	}
}
