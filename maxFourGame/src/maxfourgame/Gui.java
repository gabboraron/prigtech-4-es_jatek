package maxfourgame;




import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Sándor
 */
class Gui {
    private coordinate gameSize;
    private Game game;
    private GridButtonPanel gpanel;
    private boolean poused = false;
    
    public Gui() {
        game = new Game(3,3);
        gpanel = new GridButtonPanel(game, this, 3, 3);
        gpanel.display();
        
        showGame();
        gameSize = new coordinate(3,3);
    }

    private Gui(int row, int col) {
        game = new Game(row,col);
        gpanel = new GridButtonPanel(game, this, row, col);
        gpanel.display();
        
        showGame();
        gameSize = new coordinate(row,col);
    }
    
    /**
     * RESET BUTTON
     * @return 
     */
    /*JButton reset() {
        JButton btn = new JButton("RESET");
        
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                game.reset();
                showInitGame();
                time = 0;
            }
        });
        return btn;
    }*/

    /**
     * POUSE BUTTON
     * @return 
     */
    /*JButton pouse() {
        JButton btn = new JButton("POUSE");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(poused){
                   btn.setText("POUSE: OFF");
                   poused = false;
                   gpanel.showGameTable();
                }else{
                    btn.setText("POUSE: ON");
                    poused = true;
                    gpanel.hideGameTable();
                }
            }
        });
        return btn;
    }*/

    /**
     * Show the game panel
     */
    private void showGame() {
        for (int rowIdx = 0; rowIdx < GridButtonPanel.N; ++rowIdx)
            for(int colIdx = 0; colIdx < GridButtonPanel.M; ++colIdx){
                
                gpanel.getGridButton(rowIdx, colIdx).setText( ((Tile) game.getTileBycoordinates(new coordinate(rowIdx, colIdx))).getLabelOfTile() );
                gpanel.getGridButton(rowIdx, colIdx).setFont(new Font("Arial", Font.PLAIN, 15));
                
                //Coloring the panel
                switch (((Tile) game.getTileBycoordinates(new coordinate(rowIdx, colIdx))).getOwner()){
                    case 1 : gpanel.getGridButton(rowIdx, colIdx).setBackground(Color.green);
                             break;
                    case 2 : gpanel.getGridButton(rowIdx, colIdx).setBackground(Color.blue);
                             break;
                    case 0: gpanel.getGridButton(rowIdx, colIdx).setBackground(Color.white);
                             break;
                }
                
                
                //System.out.println("RECOLORED: " + rowIdx + ", " + colIdx);
            }
    }

    /**
     * Set a new tick to game panel
     * @param row 
     * @param col 
     */
    /*void setNewTick(int row, int col) {
        if(tickTack.isCurrentIsTick()){
            gpanel.getGridButton(row, col).setText("X");
        } else {
            gpanel.getGridButton(row, col).setText("O");
        }
        
        tickTack.addElem(new coordinate(row, col));
        if(tickTack.isItDoneByTick())
            JOptionPane.showMessageDialog(null, "You are the winner!");
    }*/
    
    /**
     * TIMER LABEL
     * @return 
     */
    int time = 0;
    JLabel timerLabel() {
        JLabel timer = new JLabel();
        timer.setText("Elapsed time: " + Integer.toString(time) + " sec");
        new java.util.Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                if(!poused){
                    ++time;
                    timer.setText("Elapsed time: " + Integer.toString(time) + " sec");
                }
                //System.out.println("Executed...");
                //your code here 
                //1000*5=5000 mlsec. i.e. 5 seconds. u can change accordngly 
            }
        },1000*5,1000*5);
        
        return timer;
    }
    
    /**
     * DISPLAY CURRENT PLAYER
     * @return 
     */
    JLabel currentPlayerLabel(int cp) {
        JLabel l = new JLabel();
        switch(cp){
            case 1 : l.setText("Current Player: Player 1");
                     l.setForeground(Color.green);
                     break;
            case 2 : l.setText("Current Player: Player 2");
                     l.setForeground(Color.blue);
                     break;
        }
        return l;
    }
    
    /**
     * CHANGE LABEL OF CURRENT PLAYER
     * @return 
     */
    private void changePlayerLabel(){
        int cp = game.currentPlayer;
        switch(cp){
            case 1 : 
                     gpanel.cpLabel.setText("Current Player: Player 1");
                     gpanel.cpLabel.setForeground(Color.green);
                     break;
            case 2 : gpanel.cpLabel.setText("Current Player: Player 2");
                     gpanel.cpLabel.setForeground(Color.blue);
                     break;
        }
    }

    /**
     * DISPLAY PLAYER 1 POINTS
     * @return 
     */
    JLabel firstPlayerPointsLabel() {
        JLabel l = new JLabel();
        l.setText("Player 1 points: " + game.player1.points);
        l.setForeground(Color.GREEN);
        return l;
    }

    /**
     * DISPLAY PLAYER 2 POINTS
     * @return 
     */
    JLabel secondPlayerPointsLabel() {
        JLabel l = new JLabel();
        l.setText("Player 2 points: " + game.player2.points);
        l.setForeground(Color.blue);
        return l;
    }
    
    /**
     * NEW GAME MENU WITH MENU OPTIONS
     * @return 
     */
    JMenu newGameMenu() {
        JMenu m = new JMenu("New game");
        gpanel.threeTimeThree = new JMenuItem(threeTimeThree);
        gpanel.fiveTimeFive = new JMenuItem(fiveTimeFive);
        gpanel.sevenTimeSeven = new JMenuItem(sevenTimeSeven);
        m.add(threeTimeThree);
        m.add(fiveTimeFive);
        m.add(sevenTimeSeven);
        
        return m;
    }
    
    /**
     * MENU ITEM
     */
    private AbstractAction threeTimeThree = new AbstractAction("3X3") {
        @Override
        public void actionPerformed(ActionEvent e)
        {                    
            Gui gui = new Gui(3,3);
        }
    };
    
    /**
     * MENU ITEM
     */
    private AbstractAction fiveTimeFive = new AbstractAction("5X5") {
        @Override
        public void actionPerformed(ActionEvent e)
        {                    
            Gui gui = new Gui(5,5);
        }
    };
    
    /**
     * MENU ITEM
     */
    private AbstractAction sevenTimeSeven = new AbstractAction("7X7") {
        @Override
        public void actionPerformed(ActionEvent e)
        {                    
            Gui gui = new Gui(7,7);
        }
    };
       
    /**
     * CHANGE LABEL OF PLAYERS POINTS
     */
    private void changePlayersPoints() {
        gpanel.fppLabel.setText("Player 1 points: " + game.player1.points);
        gpanel.sppLabel.setText("Player 2 points: " + game.player2.points);
    }

    /**
     * CHANGE PLAYERS OCCUPIED TILES NUMBERS
     */
    private void changePlayersOccupiedTiles() {
        if((game.player1.occupiedTiles != 0) || (game.player2.occupiedTiles != 0)){
            gpanel.ptLabel.setText("<html><body>Player 1 occupied:" + game.player1.occupiedTiles  + "<br>Player 2 occupied: " + (game.player2.occupiedTiles-4) + "</body></html>");
        }
    }
    
    /**
     * Change the tiles values and etc in game when a tile is clicked
     * @param row
     * @param col 
     */
    void clickOnTile(int row, int col) {
        /*( (Tile) game.getTileBycoordinates(new coordinate(row, col))).incTileAttackPoints();
        
        if(game.currentPlayer == 1){
            ( (Tile) game.getTileBycoordinates(new coordinate(row, col-1))).incTileAttackPoints();
            ( (Tile) game.getTileBycoordinates(new coordinate(row, col+1))).incTileAttackPoints();
            ( (Tile) game.getTileBycoordinates(new coordinate(row-1, col))).decTileAttackPoints();
        } else {
            ( (Tile) game.getTileBycoordinates(new coordinate(row-1, col))).incTileAttackPoints();
            ( (Tile) game.getTileBycoordinates(new coordinate(row+1, col))).incTileAttackPoints();
            ( (Tile) game.getTileBycoordinates(new coordinate(row, col-1))).decTileAttackPoints();            
        }*/
        if(!((Tile) game.getTileBycoordinates(new coordinate(row, col))).isOccupied()){
            game.clickOn(new coordinate(row, col));
            game.changePlayer();
            game.countPlayerspoints();
            changePlayerLabel();
            changePlayersPoints();
            changePlayersOccupiedTiles();

            showGame();

            if(game.isWinner()){
                poused = true;
                if(game.player1.points>game.player2.points){
                    JOptionPane.showMessageDialog(null, "PLAYER 1 WIN! CONGRATULATIONS!");
                } else {
                    JOptionPane.showMessageDialog(null, "PLAYER 2 WIN! CONGRATULATIONS!");
                }
            }
        }
    }


    

    /**
     * OPTIONS LIST
     * @return 
     */
    /*JScrollPane optionsList() {
        String[] options = {
        "8 X 5", "10 X 6", "12 X 7"};
        
        JList l = new JList(options);
        JScrollPane sp = new JScrollPane(l);
        l.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        l.addListSelectionListener((ListSelectionListener) this);
        l.setSelectedIndex(0);
        
        //@Override
        //public void valueChanged(ListSelectionEvent e) {        
        //setTitle((String)lsHonapok.getSelectedValue());
        //}
        
        return sp;
    }*/
    
}
