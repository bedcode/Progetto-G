/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

/**
 * This class represents requirements of a generic Classroom
 * @author Federico
 */
public class Requirements {

    private int capacity;
    private boolean blackboard;
    private boolean whiteboard;
    private boolean projector;
    private String specialRequirements;
/**
 * 
 * @param capacity number of people that can contain
 * @param blackboard true/false
 * @param whiteboard true/false
 * @param projector true/false
 * @param specialRequirements 
 */
    public Requirements(int capacity, boolean blackboard, boolean whiteboard, boolean projector, String specialRequirements) {
        this.capacity = capacity;
        this.blackboard = blackboard;
        this.whiteboard = whiteboard;
        this.projector = projector;
        this.specialRequirements = specialRequirements;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isBlackboard() {
        return blackboard;
    }

    public boolean isWhiteboard() {
        return whiteboard;
    }

    public boolean isProjector() {
        return projector;
    }

    public String getSpecialRequirements() {
        return specialRequirements;
    }

    /**
     *
     * @return a string with the summary of requirements of a classroom.
     */
    @Override
    public String toString() {
        return "capacità:" + capacity + ", lavagna: " + this.verifyPresence(blackboard)
                + ", lavagna lucidi: " + this.verifyPresence(whiteboard) + ", proiettore: " + this.verifyPresence(projector);
    }

    /**
     * This method verify the presence of a requirement.
     *
     * @param b requirement to check
     * @return boolean value
     */
    private String verifyPresence(Boolean b) {
        String s = null;
        if (b) {
            s = "presente";
        } else {
            s = "assente";
        }
        return s;
    }
}
