import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame implements ActionListener {

    // array with questions
    String[] questions = {
            "Which company created Java?",
            "Which year was Java created?",
            "What was Java originally called?",
            "Who is credited with creating Java?"
    };

    // 2D array with options
    String[][] options = {
            {"Sun Microsystems", "Starbucks", "Microsoft", "Alphabet"},
            {"1989", "1996", "1972", "1496"},
            {"Apple", "Latte", "Oak", "Koffing"},
            {"Steve Jobs", "Bill Gates", "James Gosling", "Mark Zuckerberg"}
    };

    // array of correct answers
    char[] answers = {
            'A',
            'B',
            'C',
            'C'
    };

    // global colors
    Color bgColor = new Color(25,25,25);
    Color fontColor = new Color(25,255,0);
    Color wrongAnswerColor = new Color(255,0,0);

    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds=10;

    // GUI components
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
    JButton retry = new JButton("Try again");

    // countdown timer in the bottom right corner
    Timer countdown = new Timer(1000, e -> {
        seconds--;
        seconds_left.setText(String.valueOf(seconds));
        if (seconds<=0){
            displayAnswer();
        }
    });

    // main body
    Quiz(){

        // setting up the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Quiz Game");
        this.setSize(650,650);
        this.getContentPane().setBackground(new Color(50,50,50));
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // top text field: used for Title and displays "Results" in the end
        textField.setBounds(0,0,650,50);
        textField.setBackground(bgColor);
        textField.setForeground(fontColor);
        textField.setFont(new Font("Ink Free", Font.BOLD,30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setText("Quiz Game");

        // displays a question
        textArea.setBounds(0,50,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(bgColor);
        textArea.setForeground(fontColor);
        textArea.setFont(new Font("MV Boli", Font.BOLD,25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
        textArea.setText("Sample text?");

        // button A
        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        // button B
        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        // button C
        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        // button D
        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        // answer A
        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(bgColor);
        answer_labelA.setForeground(fontColor);
        answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,35));

        // answer B
        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(bgColor);
        answer_labelB.setForeground(fontColor);
        answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,35));

        // answer C
        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(bgColor);
        answer_labelC.setForeground(fontColor);
        answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,35));

        // answer D
        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(bgColor);
        answer_labelD.setForeground(fontColor);
        answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,35));

        // displays countdown in the right bottom corner,
        // updates every second with the timer set up outside the Quiz body
        seconds_left.setBounds(535,510,100,100);
        seconds_left.setBackground(bgColor);
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        // "timer" label for the countdown
        time_label.setBounds(535,475,100,25);
        time_label.setBackground(bgColor);
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("MV Boli",Font.ITALIC,16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer >:D");

        // displays number of correct guesses in the end of the Quiz
        number_right.setBounds(225,225,200,100);
        number_right.setBackground(bgColor);
        number_right.setForeground(fontColor);
        number_right.setFont(new Font("Ink Free",Font.BOLD,50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        // displays percentage of correct guesses in the end of the Quiz
        percentage.setBounds(225,325,200,100);
        percentage.setBackground(bgColor);
        percentage.setForeground(fontColor);
        percentage.setFont(new Font("Ink Free",Font.BOLD,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        // displays "Try again" button in the end of the Quiz
        retry.setBounds(225,425,200,50);
        retry.setOpaque(true);
        retry.setFocusable(false);
        retry.setForeground(bgColor);
        retry.setFont(new Font("Ink Free",Font.BOLD,30));

        this.add(time_label);
        this.add(seconds_left);
        this.add(buttonA);
        this.add(buttonB);
        this.add(buttonC);
        this.add(buttonD);
        this.add(answer_labelA);
        this.add(answer_labelB);
        this.add(answer_labelC);
        this.add(answer_labelD);
        this.add(textArea);
        this.add(textField);
        this.setVisible(true);

        nextQuestion();

    }

    public void nextQuestion(){

        // main function to display content: the question and answers
        if (index>=total_questions){
            results();
        } else {
            textField.setText("Question " + (index+1));
            textArea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            countdown.start(); // starting the countdown in the bottom right corner
        }

    }

    // highlights wrong answers after users choice
    public void displayAnswer(){

        countdown.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A'){
            answer_labelA.setForeground(wrongAnswerColor);
        }if (answers[index] != 'B'){
            answer_labelB.setForeground(wrongAnswerColor);
        }if (answers[index] != 'C'){
            answer_labelC.setForeground(wrongAnswerColor);
        }if (answers[index] != 'D'){
            answer_labelD.setForeground(wrongAnswerColor);
        }

        // 2 seconds pause before the next question
        Timer pause = new Timer(2000, e -> {
                answer_labelA.setForeground(fontColor);
                answer_labelB.setForeground(fontColor);
                answer_labelC.setForeground(fontColor);
                answer_labelD.setForeground(fontColor);
                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
        });

        pause.setRepeats(false);
        pause.start();

    }

    // display results after last question
    public void results(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double) total_questions)*100);
        textField.setText("RESULTS!");
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("("+correct_guesses + "/" + total_questions+")");
        percentage.setText(result+"%");
        this.add(percentage);
        this.add(number_right);
        this.add(retry);
        retry.addActionListener(e -> newGame());
    }

    // resets everything and starts from question 1 again
    public void newGame(){
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
        index = 0;
        answer = ' ';
        correct_guesses = 0;
        total_questions = questions.length;
        result = 0;
        seconds=10;
        this.remove(percentage);
        this.remove(number_right);
        this.remove(retry);
        nextQuestion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource()==buttonA){
            answer = 'A';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }
        if (e.getSource()==buttonB){
            answer = 'B';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }

        if (e.getSource()==buttonC){
            answer = 'C';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }

        if (e.getSource()==buttonD){
            answer = 'D';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }

        displayAnswer();

    }
}
