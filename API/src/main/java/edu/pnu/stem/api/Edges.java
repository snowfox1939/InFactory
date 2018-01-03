package edu.pnu.stem.api;


import java.util.List;

import edu.pnu.stem.binder.Container;
import edu.pnu.stem.binder.IndoorGMLMap;


public class Edges {
	/**
	 * Create Edges feature instance 
	 * @param ID of Edges
	 * @param parentID ID of parent which will hold this feature
	 * @param tl list of transitions which will be held by SpaceLayer(parent)
	 * @return created Edges feature
	 */
	public edu.pnu.stem.feature.Edges createEdges(String ID, String parentID, List<Transition> tl) {
		return null;
	};

	/**
	 * Search the Edges feature in document
	 * @param ID ID of target
	 * @return searched target feature
	 */
	
	public edu.pnu.stem.feature.Edges createNodes(String docId, String parentId, String Id, List<String>transitionMember){
		edu.pnu.stem.feature.Edges newFeature = null;
		if (Container.getInstance().hasDoc(docId)) {
			newFeature.setID(Id);
			if(transitionMember != null){
				newFeature.setTransitionMembers(transitionMember);
			}
			else{
				System.out.println("Error at createNodes : there is no StateMember");
			}
			
			Container.getInstance().setFeature(docId, Id, "Edges", newFeature);
		}
		
		return newFeature;
	}
	public edu.pnu.stem.feature.Edges readEdges(String ID) {
		return null;
	};
	
	public edu.pnu.stem.feature.Edges updateNodes(String docId, String Id, String attributeType,
			String updateType, List<String>object, Boolean deleteDuality) {
		edu.pnu.stem.feature.Edges target = null;
		if (Container.getInstance().hasFeature(docId, Id)) {
			target = (edu.pnu.stem.feature.Edges)Container.getInstance().getFeature(docId, Id);
			if(attributeType.equals("transitionMember")){
				List<String>transitionMember = target.getTransitionMembers();
				if(updateType != null){
					
					if(updateType.equals("add")){
						transitionMember.addAll(object);
					}
					else if(updateType.equals("remove")){
						for(int i = 0 ; i < object.size();i++){
							if(transitionMember.contains(object.get(i))){
								transitionMember.remove(object.get(i));
								Transition.deleteTransition(docId, object.get(i),deleteDuality);
							}
							
						}
					}
				}
				target.setTransitionMembers(transitionMember);
			}
		}
		return target;
	}

	/**
	 * Search the Edges feature and edit it as the parameters
	 * @param ID ID of target
	 * @param tl list of transitions which will be held by SpaceLayer(parent)
	 * @return edited feature
	 */
	public edu.pnu.stem.feature.Edges updateEdges(String ID, List<Transition> tl) {
		return null;
	};

	/**
	 * Search the Edges feature and delete it
	 * @param ID ID of target
	 */
	public static void deleteEdges(String docId, String Id, Boolean deleteDuality) {
		
		if (Container.getInstance().hasFeature(docId, Id)) {
			IndoorGMLMap doc = Container.getInstance().getDocument(docId);
			edu.pnu.stem.feature.Edges target = (edu.pnu.stem.feature.Edges) Container.getInstance().getFeature(docId,
					Id);
			// String duality = target.getd;
			
			doc.getFeatureContainer("Nodes").remove(Id);	
			doc.getFeatureContainer("ID").remove(Id);
			for(int i = 0 ; i < target.getTransitionMembers().size();i++){
				State.deleteState(docId, target.getTransitionMembers().get(i), deleteDuality);
			}
			
		}
	};

}	