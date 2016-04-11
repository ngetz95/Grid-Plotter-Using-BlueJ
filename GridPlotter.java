// Class: GridPlotter
//
// Author: Your Name Here
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

import edu.kzoo.grid.ColorBlock;
import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.gui.GridChangeListener;
import edu.kzoo.grid.gui.nuggets.ColorChoiceMenu;

import java.awt.Color;

import javax.swing.JOptionPane;

/**
 *  Grid Plotter:<br>
 *
 *    The GridPlotter class provides methods for drawing in
 *    a grid by placing color blocks in its cells.
 *    Each drawing method should take zero arguments, should
 *    have a void return type, and should have a name that conforms
 *    to the on...ButtonClick format.  (This restriction allows the
 *    GridPlotterGUI to recognize drawing methods, for which
 *    it automatically generates buttons.)
 *
 *  @author Your Name (based on a template provided by Alyce Brady)
 *  @version Appropriate Date
 **/

public class GridPlotter implements GridChangeListener
{
  // Instance Variables: Encapsulated data for EACH GridPlotter object
    private Grid theGrid = null;
    private GridPlotterGUI display = null;
    private ColorChoiceMenu drawingColorChooser = null;

  // constructors and initialization

    /** Constructs an object that could draw in a grid.
     *      @param disp    an object that knows how to display a grid
     *      @param colorChooser  a menu for choosing the color block color
     **/
    public GridPlotter(GridPlotterGUI disp,
                       ColorChoiceMenu colorChooser)
    {
        this.display = disp;
        this.drawingColorChooser = colorChooser;
    }

    /** Sets the grid in which to draw.
     **/
    public void reactToNewGrid(Grid grid)
    {
        theGrid = grid;
    }

  // drawing methods

    /** Removes all objects from the grid.
     **/
    public void onClearGridButtonClick()
    {
        
        //     Should call ensureEmpty for every location in the grid.
        for ( int row = 0; row < theGrid.numRows(); row++ )
        {
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                ensureEmpty(row, column);
            }
        }

        // Display the grid after it has been completely cleared.
        display.showGrid();
    }

    /** Fills in all the cells of the grid using a row-major traversal.
     **/
    public void onRowMajorFillButtonClick()
    {
        for ( int row = 0; row < theGrid.numRows(); row++ )
        {
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                placeColorBlock(row, column);
            }
        }
    }
    
    public void onReverseRowMajorFillButtonClick()
    {
        for ( int row = theGrid.numRows() - 1; row >= 0; row-- )
        {
            for ( int column = theGrid.numCols() - 1; column >= 0; column-- )
            {
                placeColorBlock(row, column);
            }
        }
    }

    /** Fills in all the cells of the grid using a column-major traversal.
     **/
    public void onColMajorFillButtonClick()
    {
        for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                for ( int row = 0; row < theGrid.numRows(); row++ )
                {
                    placeColorBlock(row, column);
                }
            }
        
    }
    
    public void onReverseColMajorFillButtonClick()
    {
        for ( int column = theGrid.numCols() - 1; column >= 0; column-- )
            {
                for ( int row = theGrid.numRows() - 1; row >= 0; row-- )
                {
                    placeColorBlock(row, column);
                }
            }
        
    }
    
    public void onDiagonalButtonClick()
    {
            int row = 0;
            
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                if(row < theGrid.numRows())
                {
                    placeColorBlock(row, column);
                    row++;
                    
                }
            }
        
    }
    
    public void onTriangleButtonClick()
    {
        int row = 0;
            
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                if(row < theGrid.numRows())
                {
                    placeColorBlock(row, column);
                    row++;
                    for ( int i = 0; i < column; i++ )
                    {
                        placeColorBlock(row, i);
                    }
                }
            }
    }
    
    public void onReverseDiagonalButtonClick()
    {
            int row = 0;
            
            for ( int column = theGrid.numCols() - 1; column >= 0; column-- )
            {
                if(row >= 0)
                {
                    placeColorBlock(row, column);
                    row++;
                }
            }
        
    }
    
    public void onXButtonClick()
    {
        onDiagonalButtonClick();
        onReverseDiagonalButtonClick();
    }
    
    public void onBoxButtonClick()
    {
        for ( int row = 0; row < theGrid.numRows(); row++ )
        {
            if(row == 0 || row == theGrid.numRows() - 1)
            {
                for ( int column = 0; column < theGrid.numCols(); column++ )
                {
                    placeColorBlock(row, column);
                }
            }
            else
            {
                placeColorBlock(row, 0);
                placeColorBlock(row, theGrid.numCols()-1);
            }
        }
    }
    
    public void onBackAndForthButtonClick()
    {
        for ( int row = 0; row < theGrid.numRows(); row++ )
        {
            
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                placeColorBlock(row, column);
            }
            row++;
            for ( int column = theGrid.numCols() - 1; column >= 0; column-- )
            {
                placeColorBlock(row, column);
            }
        }
    }
    
    public void onUpAndDownButtonClick()
    {
        for ( int column = 0; column < theGrid.numCols(); column++ )
        {
            
            for ( int row = 0; row < theGrid.numRows(); row++ )
                {
                    placeColorBlock(row, column);
                }
            column++;
            for ( int row = theGrid.numRows() - 1; row >= 0; row-- )
                {
                    placeColorBlock(row, column);
                }
        }
    }
    
    public void onForthAndBackButtonClick()
    {
        for ( int row = theGrid.numRows() - 1; row >= 0; row-- )
        {
            
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                placeColorBlock(row, column);
            }
            row--;
            for ( int column = theGrid.numCols() - 1; column >= 0; column-- )
            {
                placeColorBlock(row, column);
            }
        }
    }
    
    public void onDownAndUpButtonClick()
    {
        for ( int column = theGrid.numCols() - 1; column >= 0; column-- )
        {
            
            for ( int row = 0; row < theGrid.numRows(); row++ )
                {
                    placeColorBlock(row, column);
                }
            column--;
            for ( int row = theGrid.numRows() - 1; row >= 0; row-- )
                {
                    placeColorBlock(row, column);
                }
        }
    }
    

  // helper methods that are called by other methods

    /** Ensures that the specified location is empty by removing the object
     *  there, if there is one.
     *      @param row      row number of location that should be empty
     *      @param column   column number of location that should be empty
     **/
    private void ensureEmpty(int row, int column)
    {
        // If the specified location in the grid is not empty,
        // remove whatever object is there.
        Location loc = new Location(row, column);
        if ( ! theGrid.isEmpty(loc) )
            theGrid.remove(theGrid.objectAt(loc));
    }

    /** Puts a color block in the specified location and redisplays the grid.
     *      @param row      row in which to place the new color block
     *      @param column   column in which to place the new color block
     **/
    private void placeColorBlock(int row, int column)
    {
        // First remove any color block that happens to be at this location.
        ensureEmpty(row, column);

        // Determine the color to use for this color block.
        Color color = drawingColorChooser.currentColor();

        // Construct the color block and add it to the grid at the
        // specificed location.  Then display the grid.
        Location loc = new Location(row, column);
        theGrid.add(new ColorBlock(color), loc);
        display.showLocation(loc);
    }

}
