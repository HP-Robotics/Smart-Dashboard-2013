import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.net.TCPImageFetcher;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;


public class NetworkTableWidget extends StaticWidget implements ITableListener {	
//	private double midTargetDistance;
//	private double highTargetDistance;
	private int valueChangedCount;
	private double shooterSpeed;
	private ITable table;
	private int highX;
	private int highY;
	private int PerfectY;
	private int isHighGoal;

//	private TCPImageFetcher camera;
//		
//	private BufferedImage lastCameraImage;
//	private String cameraError;
	
//	private String socketError;
//	private Socket input;
//	private InputStream inputStream;

	private int camX=0;
	private int camWidth=960;
	private int camY=0;
	private int camHeight=720;
	
	@Override
	public void propertyChanged(Property arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(camWidth, camHeight);
	}
	

	@Override
	public void init() {
		setSize(camWidth, camHeight);
		setLocation(camX, camY);
		
//		try {
//			input = new Socket(InetAddress.getByAddress(new byte[] { 10, 28, 23, 11 }), 80);
//			inputStream = input.getInputStream();
//		} catch (UnknownHostException e1) {
//			socketError = e1.getMessage();
//		} catch (IOException e1) {
//			socketError = e1.getMessage();
//		}
		
		
		
		table = Robot.getTable();
		table.addTableListener(this);
		
//		camera = new TCPImageFetcher(2823);
		
//		Thread animator = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while(isVisible()) {
//					synchronized(NetworkTableWidget.this) {
//						try {
//							lastCameraImage =  camera.fetch();
//							cameraError = null;
//						} catch (Exception e) {
//							cameraError = e.getMessage();
//							lastCameraImage = null;
//						}
//					}
//					
//					repaint();
//					
//					try {
//						Thread.sleep(1000);
//					} catch(InterruptedException e) {
//						// do nothing
//					}
//				}
//			}
//		}, "animator");
//		animator.setDaemon(true);
//		animator.start();
		
//		repaint();
	}

	@Override
	public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
/*		if (arg1.equals("midD")) {
			midTargetDistance = arg0.getNumber(arg1);
		}
		else if (arg1.equals("highD")) {
			highTargetDistance = arg0.getNumber(arg1);
		}
*/
		if (arg1.equals("highX")) {
			highX = (int) arg0.getNumber(arg1);
		}
		else if (arg1.equals("highY")) {
			highY = (int) arg0.getNumber(arg1);
		}
		else if (arg1.equals("PerfectY")) {
			PerfectY = (int) arg0.getNumber(arg1);
		}
		else if (arg1.equals("speed")) {
			shooterSpeed = arg0.getNumber(arg1);
		}
		else if (arg1.equals("HighGoalNumber")) {
			isHighGoal = (int) arg0.getNumber(arg1);
		}
		valueChangedCount++;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.setColor(Color.BLACK);
		//g2d.fillRect(camX, camY, camWidth, camHeight);
		g2d.setColor(Color.WHITE);
		
/*		if (lastCameraImage != null) {
			g2d.drawImage(lastCameraImage, camX, camY, camWidth, camHeight, null);
		}
		if (cameraError != null) {
			g2d.drawString(cameraError, 10, 30);
		}
		
		if (socketError != null) {
			g2d.drawString(socketError, 10, 110);
		}
		if (inputStream != null) {
			try {
				g2d.drawString("" + inputStream.read(), 10, 130);
			} catch (IOException e) {
				socketError = e.getMessage();
			}
		}
*/		
		//g2d.drawString("midD: " + midTargetDistance, camX+10, camY+50);
		//g2d.drawString("highD: " + highTargetDistance, camX+10, camY+70);
		g2d.drawString("update count: " + valueChangedCount, camX+10, camY+70);
		g2d.drawString("speed: " + shooterSpeed, camX+10, camY+50);
		//g2d.setColor(new Color(1.0f, 1.0f, 1.0f));
		g2d.setColor(Color.RED);
		//g2d.setColor();
		//g2d.fillRect(0, 0, 20, 20);
		
		
		if (isHighGoal==1.0) {
			g2d.drawOval((highX*camWidth)/320-10, (highY*camHeight)/240-10, 20, 20);
			Polygon p = new Polygon(new int[] { camWidth/2-10, camWidth/2+10,camWidth/2+10, camWidth/2-10 }, new int[] { ((PerfectY*camHeight)/240+10),  ((PerfectY*camHeight)/240+10),  ((PerfectY*camHeight)/240-10),  ((PerfectY*camHeight)/240-10) }, 4);
			g2d.drawPolygon(p);
		}
		
		//g2d.fill(p);
		repaint();
		
		
		
	
	
	}	
}