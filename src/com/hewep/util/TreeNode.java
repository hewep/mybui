package com.hewep.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private String id;
	private String pid;
	private String text;
	private String homePage;
	private String href;
	private Boolean closeable = true;
	private List<TreeNode> menu = new ArrayList<TreeNode>();
	private List<TreeNode> items = new ArrayList<TreeNode>();
	
	
	public TreeNode(){}
	
	public TreeNode(String id, String pid, String text){
		this.id = id;
		this.pid = pid;
		this.text = text;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Boolean getCloseable() {
		return closeable;
	}

	public void setCloseable(Boolean closeable) {
		this.closeable = closeable;
	}

	public List<TreeNode> getMenu() {
		return menu;
	}

	public void setMenu(List<TreeNode> menu) {
		this.menu = menu;
	}

	public List<TreeNode> getItems() {
		return items;
	}

	public void setItems(List<TreeNode> items) {
		this.items = items;
	}
	
	public void addItem(TreeNode node){
		this.items.add(node);
	}

}
