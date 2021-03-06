package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import serialzation.DealXmlSax;
import serialzation.ReadConfigFromXml;
import singleton.Project;
import util.CopyFiles;
import util.DeleteDir;
import util.GenReports;

public class MainFrame extends JFrame implements ActionListener,
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainFrame instance;
	private final int width = 1350;
	private final int height = 900;
	private SpringLayout springlayout = new SpringLayout();
	private JPanel mainJPanel = new JPanel();
	private JLabel messageJLabel = new JLabel();
	private JTabbedPane functionJPanel = new JTabbedPane();
	private JLabel functionJLabel = new JLabel();
	private JToolBar toolBar = new JToolBar();
	private JButton[] toolBarButton = new JButton[] { new JButton(""),
			new JButton(""), new JButton(""), new JButton(""), };
	private JMenuBar jmenuBar = new JMenuBar();
	private JMenu[] jmenuArray = { new JMenu("文件"), new JMenu("功能"),
			new JMenu("帮助") };
	private JMenuItem[] jmenuItemforJmenu1 = { new JMenuItem("关闭当前项目"), };
	private JMenuItem[] jmenuItemforJmenu2 = { new JMenuItem("清空生成的变异体"),
			new JMenuItem("清空测试结果") };
	private JMenuItem[] jmenuItemforJmenu3 = { new JMenuItem("文档"), };
	private Boolean toolBarIsHorizontal = true;
	private String projectName = "";

	private MainFrame() {
		this.projectName = Project.getInstance().getSelectProject();

		init();
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void reload() {
		this.projectName = Project.getInstance().getSelectProject();
		messageJLabel.setText(" " + Project.getInstance().getSelectProject());
		this.setTitle("MuDroid : " + Project.getInstance().getSelectProject());
		functionJPanel.removeAll();
		InfoJPanel pjp = new InfoJPanel(this.projectName);
		ChooseFileAndOpPanel mjp = new ChooseFileAndOpPanel(this.projectName);
		MutantRunJPanel rjp = new MutantRunJPanel(this.projectName);
		TesResultInfoJPanel trij = new TesResultInfoJPanel(this.projectName);
		functionJPanel.addTab("MuDroid介绍", pjp);
		functionJPanel.addTab("选择变异文件和变异算子", mjp);

		functionJPanel.addTab("选择变异体", rjp);
		if (!new File(Project.getInstance().getRunTestLogPath()).exists()) {
			functionJPanel.addTab("变异测试结果", new JLabel("尚无测试结果"));
		} else {
			functionJPanel.addTab("变异测试结果", trij);
		}

		this.validate();
		this.repaint();
	}

	public void reload2() {

		LoadingFrame.getInstance().setVisible(false);
		this.setVisible(true);
		this.requestFocus();
	}

	private void init() {
		// TODO Auto-generated method stub

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				functionJPanel.removeAll();
				System.exit(0);
			}
		});

		ImageIcon icon = new ImageIcon(Project.getInstance().getConfigDir()
				+ "/res/mudroid.png");
		this.setIconImage(icon.getImage());
		this.setTitle("MuDroid : " + Project.getInstance().getSelectProject());
		int iconwidth = 20;
		int iconheight = 20;
		ImageIcon icon0 = new ImageIcon(Project.getInstance().getConfigDir()
				+ "/res/run.png");
		icon0.setImage(icon0.getImage().getScaledInstance(iconwidth,
				iconheight, Image.SCALE_DEFAULT));
		toolBarButton[0].setIcon(icon0);
		ImageIcon icon1 = new ImageIcon(Project.getInstance().getConfigDir()
				+ "/res/find.png");
		icon1.setImage(icon1.getImage().getScaledInstance(iconwidth,
				iconheight, Image.SCALE_DEFAULT));
		toolBarButton[1].setIcon(icon1);
		ImageIcon icon2 = new ImageIcon(Project.getInstance().getConfigDir()
				+ "/res/save.png");
		icon2.setImage(icon2.getImage().getScaledInstance(iconwidth,
				iconheight, Image.SCALE_DEFAULT));
		toolBarButton[2].setIcon(icon2);
		ImageIcon icon3 = new ImageIcon(Project.getInstance().getConfigDir()
				+ "/res/refresh.png");
		icon3.setImage(icon3.getImage().getScaledInstance(iconwidth,
				iconheight, Image.SCALE_DEFAULT));
		toolBarButton[3].setIcon(icon3);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		int startLocationX = (screenWidth - width) / 2;
		int startLocationY = (screenHeight - height) / 2;

		for (int i = 0; i < jmenuArray.length; i++) {
			jmenuBar.add(jmenuArray[i]);
		}
		for (int i = 0; i < jmenuItemforJmenu1.length; i++) {
			jmenuArray[0].add(jmenuItemforJmenu1[i]);
			jmenuItemforJmenu1[i].addActionListener(this);
		}
		for (int i = 0; i < jmenuItemforJmenu2.length; i++) {
			jmenuArray[1].add(jmenuItemforJmenu2[i]);
			jmenuItemforJmenu2[i].addActionListener(this);
		}
		for (int i = 0; i < jmenuItemforJmenu3.length; i++) {
			jmenuArray[2].add(jmenuItemforJmenu3[i]);
			jmenuItemforJmenu3[i].addActionListener(this);
		}
		mainJPanel.setLayout(springlayout);
		mainJPanel.add(toolBar);
		toolBar.addPropertyChangeListener(this);
		for (int i = 0; i < toolBarButton.length; i++) {
			toolBar.add(toolBarButton[i]);
			toolBarButton[i].addActionListener(this);
		}
		springlayout.putConstraint(SpringLayout.NORTH, toolBar, 0,
				SpringLayout.NORTH, mainJPanel);

		mainJPanel.add(functionJPanel);

		functionJPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		functionJLabel.setText(functionJLabel.getWidth() + ":"
				+ functionJLabel.getHeight());
		mainJPanel.add(messageJLabel);

		messageJLabel.setText(" " + Project.getInstance().getSelectProject());
		springlayout.putConstraint(SpringLayout.SOUTH, messageJLabel, 0,
				SpringLayout.SOUTH, mainJPanel);

		toolBar.setOrientation(SwingConstants.VERTICAL);

		if (toolBar.getOrientation() == SwingConstants.HORIZONTAL)
			toolBarIsHorizontal = true;
		else if (toolBar.getOrientation() == SwingConstants.VERTICAL)
			toolBarIsHorizontal = false;
		drawToolBarAndFunction(toolBarIsHorizontal);

		this.setJMenuBar(jmenuBar);
		this.add(mainJPanel);
		this.setBounds(startLocationX, startLocationY, width, height);
	}

	private void drawToolBarAndFunction(Boolean toolBarIsHorizontal) {
		// TODO Auto-generated method stub
		if (toolBarIsHorizontal) {
			functionJPanel.setTabPlacement(JTabbedPane.LEFT);
			springlayout.putConstraint(SpringLayout.NORTH, functionJPanel, 0,
					SpringLayout.SOUTH, toolBar);
			Spring jtfw = Spring.constant(0, 100, 400);
			springlayout.putConstraint(SpringLayout.SOUTH, functionJPanel,
					jtfw, SpringLayout.NORTH, functionJPanel);
			springlayout.putConstraint(SpringLayout.SOUTH, mainJPanel, 20,
					SpringLayout.SOUTH, functionJPanel);
			springlayout.putConstraint(SpringLayout.WEST, functionJPanel, 13,
					SpringLayout.WEST, mainJPanel);
			springlayout.putConstraint(SpringLayout.EAST, mainJPanel, 13,
					SpringLayout.EAST, functionJPanel);
		} else {
			functionJPanel.setTabPlacement(JTabbedPane.TOP);
			springlayout.putConstraint(SpringLayout.WEST, functionJPanel, 0,
					SpringLayout.EAST, toolBar);
			Spring jtfw = Spring.constant(0, 100, 400);
			springlayout.putConstraint(SpringLayout.EAST, functionJPanel, jtfw,
					SpringLayout.WEST, functionJPanel);
			springlayout.putConstraint(SpringLayout.EAST, mainJPanel, 13,
					SpringLayout.EAST, functionJPanel);
			springlayout.putConstraint(SpringLayout.NORTH, functionJPanel, 13,
					SpringLayout.NORTH, mainJPanel);
			springlayout.putConstraint(SpringLayout.SOUTH, mainJPanel, 20,
					SpringLayout.SOUTH, functionJPanel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == toolBarButton[0]) {
			messageJLabel.setText("打开命令行");
			try {
				// Desktop.getDesktop().open(new
				// File(Project.getInstance().getConfigDir()+"/open.bat"));
				System.out.println("cmd.exe /k start "
						+ Project.getInstance().getEmuExePath()
						+ "/emulator.exe  @"
						+ Project.getInstance().getEmuAvdName());
				Runtime.getRuntime().exec(
						"cmd.exe /c start "
								+ Project.getInstance().getEmuExePath()
								+ "/emulator.exe  @"
								+ Project.getInstance().getEmuAvdName());

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// E:/Users/Supermans1201/AppData/Local/Android/sdk/tools/emulator.exe
			// @Nexus_5X_API_24
		}
		if (e.getSource() == toolBarButton[1]) {
			messageJLabel.setText("打开项目目录");
			try {
				Desktop.getDesktop().open(
						new File(Project.getInstance().getSelectProject()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == toolBarButton[2]) {
			messageJLabel.setText("重新加载数据");
			DealXmlSax dxs = new ReadConfigFromXml();
			try {
				dxs.run(new String[] { Project.getInstance().getConfigPath() });
				GenReports.GenReport(null);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		if (e.getSource() == toolBarButton[3]) {
			messageJLabel.setText("刷新当前面板");
			this.reload();
			this.validate();
			this.repaint();
		}

		if (e.getSource() == jmenuItemforJmenu1[0]) {
			System.out.println(e.getSource());
			{
				this.setVisible(false);
				EstablishFrame.getInstance().setVisible(true);
				EstablishFrame.getInstance().reload();

			}
		}
		if (e.getSource() == jmenuItemforJmenu2[0]) {
			System.out.println(e.getSource());
			DeleteDir.deleteMut();
		}

		if (e.getSource() == jmenuItemforJmenu2[1]) {
			System.out.println(e.getSource());
			if (new File(Project.getInstance().getRunTestLogPath()).exists()) {
				CopyFiles.copyFile(new File(Project.getInstance()
						.getRunTestLogPath()), new File(Project.getInstance()
						.getRunTestLogPath() + new Date()), true);
				new File(Project.getInstance().getRunTestLogPath()).delete();
			}
		}

		if (e.getSource() == jmenuItemforJmenu3[0]) {
			System.out.println(e.getSource());
			{
				this.setVisible(false);
			}
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		boolean ishorizontal = true;
		if (toolBar.getOrientation() == SwingConstants.VERTICAL) {
			ishorizontal = false;
		}
		if (toolBar.getOrientation() == SwingConstants.HORIZONTAL) {
			ishorizontal = true;
		}
		toolBarIsHorizontal = ishorizontal;
		drawToolBarAndFunction(ishorizontal);
	}

	public static MainFrame getInstance() {
		if (instance == null) {

			instance = new MainFrame();
		}
		return instance;
	}

}
