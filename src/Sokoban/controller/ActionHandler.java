package Sokoban.controller;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface ActionHandler extends MouseListener, MouseMotionListener, KeyListener {

	public void paint(Graphics2D g);

}