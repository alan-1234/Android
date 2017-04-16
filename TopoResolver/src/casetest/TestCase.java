package casetest;

import java.util.ArrayList;
import java.util.List;

import base.Utils;
import bookmark.Bookmark;
import toporesolver.TopoResolver;
import toporesolver.TopoResolver.INode;

public abstract class TestCase {
	
	protected List<INode> caseData = new ArrayList<>();
	
	public abstract void prepareCase();
	public void run(){
		
		INode head;
		
		prepareCase();
		
		
		logCase(caseData);
		head = TopoResolver.resolve(new ArrayList<INode>(caseData));
		checkAndLog(head, caseData);
		
	}
	
	private void logCase(List<INode> nodes){
		
		System.out.println("case start>>>>>>>>>>>>>>>>>");
		for(INode n:nodes){
			System.out.println(n.identifier() + "  " + n.nextId() + "  " + n.nextId2() + "  " + n.priority());
		}
	}
	
	private void checkAndLog(INode head,List<INode> nodes){
		String log = "";
		while(head != null){
			String id = head.identifier();
			log += id;
			
			id= head.nextId();
			head = null;
			for(INode n:nodes){
				if(!Utils.stringEmpty(id) && id.equals(n.identifier())){
					head = n;
					break;
				}
			}
		}
		
		System.out.println(log);
	}
	
	
	protected static final class TestBookmarkBuilder{
		
		private Bookmark bookmark;
		
		public TestBookmarkBuilder(String name){
			bookmark = new Bookmark(name);
		}
		
		public TestBookmarkBuilder local_next(String val){
			bookmark.local_next = val;
			return this;
		}
		
		public TestBookmarkBuilder cloud_next(String val){
			bookmark.cloud_next = val;
			return this;
		}
		
		public TestBookmarkBuilder order_time(long val){
			bookmark.order_time = val;
			return this;
		}
		
		public TestBookmarkBuilder dirty(int val){
			bookmark.dirty = val;
			return this;
		}
		
		public Bookmark build(){
			return bookmark;
		}
	}

}
