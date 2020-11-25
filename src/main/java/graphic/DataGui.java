package graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chatbot.App;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;

public class DataGui extends JFrame {

	private JPanel contentPane;
	private JTextField idNumThreads;
	private JTextField idInReqThreads;
	private JLabel idReqThread;
	private JLabel idTotalNumberReq;
	private JLabel idAvgTime;
	private JLabel idMaxTime;
	private JLabel idMinTime;
	private JLabel idTotalTime;
	private JButton idButtonGraphic;
	private JPanel idPanelGraphic;
	
	public DataGui() {
		setResizable(false);
		setTitle("AREP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 1010;
		int height = 700;
		//setBounds(((screenSize.width / 2) - (width / 2)), ((screenSize.height / 2) - (height / 2)), 500, 500);
		setBounds(((screenSize.width / 2) - (width / 2)), ((screenSize.height / 2) - (height / 2)), width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idPanelGraphic = new JPanel();
		idPanelGraphic.setBackground(SystemColor.textHighlight);
		idPanelGraphic.setBounds(0, 0, 996, 523);
		contentPane.add(idPanelGraphic);
		idPanelGraphic.setLayout(null);
		
		JPanel idPanelLabels = new JPanel();
		idPanelLabels.setBounds(0, 533, 996, 139);
		contentPane.add(idPanelLabels);
		idPanelLabels.setLayout(null);
		
		idNumThreads = new JTextField();
		idNumThreads.setBounds(160, 33, 96, 19);
		idPanelLabels.add(idNumThreads);
		idNumThreads.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("NumThreads:");
		lblNewLabel.setBounds(34, 36, 85, 13);
		idPanelLabels.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Request/Thread:");
		lblNewLabel_1.setBounds(34, 66, 116, 13);
		idPanelLabels.add(lblNewLabel_1);
		
		idInReqThreads = new JTextField();
		idInReqThreads.setBounds(160, 62, 96, 19);
		idPanelLabels.add(idInReqThreads);
		idInReqThreads.setColumns(10);
						
		idReqThread = new JLabel("Number of requests/thread: ");
		idReqThread.setBounds(334, 27, 220, 13);
		idPanelLabels.add(idReqThread);
		
		idTotalNumberReq = new JLabel("Total number of requests: ");
		idTotalNumberReq.setBounds(334, 50, 220, 13);
		idPanelLabels.add(idTotalNumberReq);
		
		idAvgTime = new JLabel("Average of the requests: ");
		idAvgTime.setBounds(334, 73, 220, 13);
		idPanelLabels.add(idAvgTime);
		
		idMaxTime = new JLabel("Maximum response time: ");
		idMaxTime.setBounds(600, 50, 220, 13);
		idPanelLabels.add(idMaxTime);
		
		idMinTime = new JLabel("Minimun response time: ");
		idMinTime.setBounds(600, 73, 220, 13);
		idPanelLabels.add(idMinTime);
		
		idTotalTime = new JLabel("Total time of requests: ");
		idTotalTime.setBounds(600, 27, 220, 13);
		idPanelLabels.add(idTotalTime);
		
		idButtonGraphic = new JButton("Graphic");
		idButtonGraphic.setBounds(868, 82, 85, 21);
		idPanelLabels.add(idButtonGraphic);
		
		createActions();
	}
	
	public void createActions() {
		idButtonGraphic.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshLabels();
				validateInputs();
			}
		});
	}
	
	public void refreshLabels() {
		idReqThread.setText("Number of requests/thread: ");
		idTotalTime.setText("Total time of requests: ");
		idTotalNumberReq.setText("Total number of requests: ");
		idMaxTime.setText("Maximum response time: ");
		idAvgTime.setText("Average of the requests: ");
		idMinTime.setText("Minimun response time: ");
	}
	
	public void validateInputs() {
		String threads = idNumThreads.getText();
		String reqThreads = idInReqThreads.getText();
		boolean flag = false;
		if (threads.equals("") || reqThreads.equals("")) {
			JOptionPane.showConfirmDialog(null, "Fields are required!", "Exit Confirmation", JOptionPane.CLOSED_OPTION);
			return;
		}
		int numThreads;
		int intReqThreads;
		try {
			numThreads = Integer.parseInt(threads);
			intReqThreads = Integer.parseInt(reqThreads);
		} catch (NumberFormatException e) {
			JOptionPane.showConfirmDialog(null, "Only Numbers!", "Exit Confirmation", JOptionPane.CLOSED_OPTION);
			return;
		}
		
		App app = new App(numThreads, intReqThreads);
		String[] data = app.getResults();
		graphicThings(data);
		graphicTimes(app.getListTimes());
	}
	
	public void graphicThings(String[] data) {
		idReqThread.setText(idReqThread.getText() + data[0]);
		idReqThread.setForeground(new Color(126, 3, 145));
		idTotalTime.setText(idTotalTime.getText() + data[1]);
		idTotalTime.setForeground(new Color(57, 28, 151));
		idTotalNumberReq.setText(idTotalNumberReq.getText() + data[2]);
		idTotalNumberReq.setForeground(new Color(225, 121, 17));
		idMaxTime.setText(idMaxTime.getText() + data[3]);
		idMaxTime.setForeground(Color.red);
		idAvgTime.setText(idAvgTime.getText() + data[4]);
		idAvgTime.setForeground(new Color(70, 70, 70));
		idMinTime.setText(idMinTime.getText() + data[5]);
		idMinTime.setForeground(new Color(28, 151, 77));		
	}
	
	public void graphicTimes(ArrayList<Double> listTimes) {
		GraphicData graphicData = new GraphicData(listTimes);
		idPanelGraphic.setLayout(new BorderLayout());
		idPanelGraphic.add(graphicData.createGraphic());
		idPanelGraphic.validate();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataGui frame = new DataGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
}
