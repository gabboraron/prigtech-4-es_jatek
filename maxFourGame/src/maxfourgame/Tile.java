package maxfourgame;

import java.util.Objects;

/**
 *
 * @author Sándor
 */
public class Tile {
    private boolean occupied; 
    private int owner;
    private coordinate coord;
    private Game game;
    
    private int ptToOccupation;
    //private int currentPoints;
    private int nrOfAttacks;
    private int nrOfAttacksNeeded;
    
    public Tile(Game game, coordinate coord) {
        this.game = game;
        occupied = false;
        nrOfAttacks = 0;
        this.coord = coord;
        occupied = false;
        nrOfAttacksNeeded = 4;
        ptToOccupation = 1;
        owner = 0;
    }

    /**
     * Get if this tile is occupied
     * @return 
     */
    public boolean isOccupied() {
        return occupied;
    }
    
    /**
     * Get the label what is on the tile in game panel
     * @return 
     */
    public String getLabelOfTile(){
        String s = "";
        if(!isOccupied())
            s += nrOfAttacks;
        
        return s;
    }
    
    /**
     * Increment of the attack points in this tile. Also change the tile settings if it necessary;
     */
    public void incTileAttackPoints(){
        if(!isOccupied()){
            ++nrOfAttacks;
            
            if(nrOfAttacks == nrOfAttacksNeeded){
                occupied = true;
                switch(game.currentPlayer){
                    case 1 : owner = 1; break;
                    case 2 : owner = 2; break;
                }
            }
        }
    }
        
    @Override
    public String toString() {
        return String.format(" nrOfAttacks:" + nrOfAttacks + " coordinates: " + coord + "\n");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.occupied ? 1 : 0);
        hash = 89 * hash + this.owner;
        hash = 89 * hash + Objects.hashCode(this.coord);
        hash = 89 * hash + Objects.hashCode(this.game);
        hash = 89 * hash + this.ptToOccupation;
        hash = 89 * hash + this.nrOfAttacks;
        hash = 89 * hash + this.nrOfAttacksNeeded;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tile other = (Tile) obj;
        if (this.occupied != other.occupied) {
            return false;
        }
        if (this.owner != other.owner) {
            return false;
        }
        if (this.ptToOccupation != other.ptToOccupation) {
            return false;
        }
        if (this.nrOfAttacks != other.nrOfAttacks) {
            return false;
        }
        if (this.nrOfAttacksNeeded != other.nrOfAttacksNeeded) {
            return false;
        }
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        return true;
    }

    

    /**
     * Get Coordinates of tile
     * @return 
     */
    public coordinate getCoord() {
        return coord;
    }

    /**
     * Get the owner of this tile.
     * 0 - nobody
     * 1 - Player 1
     * 2 - Player 2
     * 404 - invalid Terrain
     * @return 
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Get how much points earns this terrain.
     * @return 
     */
    public int getPtToOccupation() {
        return ptToOccupation;
    }
    
}

