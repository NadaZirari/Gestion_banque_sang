package model;

public enum GroupeSanguin {
	
	
    O_NEG, O_POS, A_NEG, A_POS, B_NEG, B_POS, AB_NEG, AB_POS;


    
    
    //ajouter function toString 
    @Override
    public String toString() {
        switch (this) {
            case O_NEG: return "O-";
            case O_POS: return "O+";
            case A_NEG: return "A-";
            case A_POS: return "A+";
            case B_NEG: return "B-";
            case B_POS: return "B+";
            case AB_NEG: return "AB-";
            case AB_POS: return "AB+";
            default: return name();
        }
        
    }
}
