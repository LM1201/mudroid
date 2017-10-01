package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import singleton.Project;
import util.GenReports;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class TesResultInfoJPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel functionPanel = new JPanel();
	
	final JWebBrowser webBrowser = new JWebBrowser();
	String projectName ="";

	void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		functionPanel.setLayout(new BoxLayout(functionPanel,
				BoxLayout.LINE_AXIS));
		functionPanel.setBorder(BorderFactory.createEtchedBorder());
		webBrowser.setBarsVisible(false);  
		webBrowser.navigate(Project.getInstance().getConfigDir()+"/html/index.html");

		functionPanel.add(webBrowser, BorderLayout.CENTER);
		this.add(functionPanel);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	TesResultInfoJPanel() {
		this.projectName = Project.getInstance().getSelectProject();
		GenReports.GenReport(null);
		init();
	}

	TesResultInfoJPanel(String projectName) {
		this.projectName = projectName;
		init();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}