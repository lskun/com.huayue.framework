/**
 * 
 */
package com.huayue.library.common;

import java.util.*;
/**
 * @author lsk0414
 *
 */
public class HierarchicalRelationHelper {
	
	/**
	 * 
	 * @param set
	 * @param nodeId
	 * @param isRoot
	 * @return
	 */
	public static List<TreeNode> getListForSelect(Set<TreeNode> set,Integer nodeId ){
		List<TreeNode> list = new ArrayList<TreeNode>();
		boolean isRoot = nodeId == null ? true : false;
		if(isRoot){
				TreeNode rootNode = new TreeNode();
				rootNode.setId(0);
				addChildToList(list ,set ,rootNode);
		}else{
			for(TreeNode node : set){
				if(node.getId() == nodeId){
					list.add(node);
					addChildToList(list ,set ,node);
					break;
				}
			}
		}		
		return list;
	}
	
	/**
	 * 
	 * @param list
	 * @param set
	 * @param node
	 */
	public static void addChildToList(List<TreeNode> list,Set<TreeNode> set,TreeNode node){
		for(TreeNode tn : set){
			if(tn.getParentId() == node.getId()){
				tn.setDeep(node.getDeep() + 1);
				list.add(tn);
				addChildToList(list ,set ,tn);
			}
		}
	}
	
	public static void main(String[] args){
		Set<TreeNode> set = new TreeSet<TreeNode>(new Comparator<TreeNode>(){
			public int compare(TreeNode o1, TreeNode o2) {
				if(o1.getId()>o2.getId()) return 1;
				if(o1.getId()<o2.getId()) return -1;
				return 0;
			}
			
		});
		set.add(new TreeNode(0,"a",1));
		set.add(new TreeNode(0,"a",7));
		set.add(new TreeNode(1,"a",2));
		set.add(new TreeNode(1,"a",3));
		set.add(new TreeNode(3,"a",4));
		set.add(new TreeNode(3,"a",5));
		set.add(new TreeNode(4,"a",6));
		
		List<TreeNode> l = getListForSelect(set,null);
		
		for(TreeNode n : l){
			System.out.println(n.toString());
		}
		
	}
}
