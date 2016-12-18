package com.blackpensoftware.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.blackpensoftware.fileIO.ConfigurationHandler;
import com.blackpensoftware.fileIO.Settings;
import com.blackpensoftware.core.LWJGE_Window;
import com.blackpensoftware.fileIO.LogHandler;
import com.blackpensoftware.panels.LauncherPanel;
import com.blackpensoftware.panels.SavePanel;

public class LauncherFrame extends JFrame{
	
	static LogHandler log = new LogHandler();
	static ConfigurationHandler config;
	static Settings settings = new Settings();
	
	private static LWJGE_Window gameView = new LWJGE_Window();
	
	
	private Color bgColor = Color.DARK_GRAY;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private double width = screenSize.getWidth();
	private double height = screenSize.getHeight();
	
	private int frameWidth = (int)(width / 2);
	private int frameHeight = (int)(height / 2);
	
	private int framePositionX = frameWidth / 2;
	private int framePositionY = frameHeight / 2;
	
	private String title = "Game";
	
	private LauncherPanel launchPanel = new LauncherPanel();
	private SavePanel savePanel = new SavePanel();
	
	public LauncherFrame(){
		this.setVisible(true);
		this.setSize(frameWidth, frameHeight);
		this.setBackground(bgColor);
		this.setLocation(framePositionX, framePositionY);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.add(launchPanel);
	}// End of constructor

	public int getWidth() {
		return (int) width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return (int) height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getFramePositionX() {
		return framePositionX;
	}

	public void setFramePositionX(int framePositionX) {
		this.framePositionX = framePositionX;
	}

	public int getFramePositionY() {
		return framePositionY;
	}

	public void setFramePositionY(int framePositionY) {
		this.framePositionY = framePositionY;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static LWJGE_Window getGameView() {
		return gameView;
	}

	public void setGameView(LWJGE_Window gameView) {
		this.gameView = gameView;
	}

	public static LogHandler getLog() {
		return log;
	}

	public static void setLog(LogHandler log) {
		LauncherFrame.log = log;
	}

	public static ConfigurationHandler getConfig() {
		return config;
	}

	public void setConfig(ConfigurationHandler config) {
		this.config = config;
	}

	public static Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public void toggleVisible() {
		this.setVisible(!this.isVisible());
	}

	public LauncherPanel getLaunchPanel() {
		return launchPanel;
	}

	public void setLaunchPanel(LauncherPanel launchPanel) {
		this.launchPanel = launchPanel;
	}

	public SavePanel getSavePanel() {
		return savePanel;
	}

	public void setSavePanel(SavePanel savePanel) {
		this.savePanel = savePanel;
	}
}// End of class
