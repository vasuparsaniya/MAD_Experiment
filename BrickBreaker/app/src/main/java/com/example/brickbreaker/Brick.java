package com.example.brickbreaker;

public class Brick {
    private boolean isVisible;  // manage visibility of brick
    public int row, column, width, height;

    public Brick(int row, int column, int width, int height){
        isVisible = true;  //by default every brick is visible
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
    }

    public void setInvisible(){
        isVisible = false;
    }

    public boolean getVisibility(){
        return isVisible;
    }
}

/*Using this class draw bricks and manage the fesibility of bricks*/
