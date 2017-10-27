/**
 * Created by Vern Technologies on 7/19/17. For package VTFXcontrols.
 */

package VTFXcontrols;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <h1>SwitchToggle Class</h1>
 * Creates a default or customizable Toggle Switch for the JavaFx platform.
 * <p>
 * Has builtin functionality to check the Toggle Switch's state and to be able
 * to set it's state programmatically. You can also evaluate the
 * Toggle Switch's state <code>switchOnProperty()</code> for Listeners.
 *
 * @author Matthew Ashley
 * @since Version 0.0.0.1
 *
 */

public class SwitchToggle extends HBox {

    private final Label defaultLabel = new Label("OFF");
    private  Label label = new Label();
    private  Button button = new Button();
    private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
    private boolean selected;
    private boolean toggleSwitch = false;
    private double initWidth;
    private String onState;
    private String offState;
    private Text text;

    Color falseColored;
    Color trueColored;

    Timer timer = new Timer();

    /**
     * @return Returns the state of the Toggle Switch for Listeners.
     */
    public SimpleBooleanProperty switchOnProperty() {return switchedOn;}

    /**
     * @return Returns the state of the Toggle Switch as either true or false.
     */
    public boolean isSelected() {return selected;}

    /**
     * Sets the Toggle Switch to the specified state, either true or false.
     * @param state contains the inputted boolean value.
     */
    public void setSelected(boolean state) {
        if (toggleSwitch) {
            if (state) {
                label.setText(onState);
                setStyle(String.format("-fx-background-color: #%s ; -fx-background-radius: 20;", colorToHex(trueColored)));
                label.toFront();
            } else {
                label.setText(offState);
                setStyle(String.format("-fx-background-color: #%s ; -fx-background-radius: 20;", colorToHex(falseColored)));
                button.toFront();
            }
        } else {
            if (state) {
                label.setText("ON");
                setStyle("-fx-background-color: #a7ef88; -fx-background-radius: 20;");
                label.toFront();
            } else {
                label.setText("OFF");
                setStyle("-fx-background-color: #aeb0b2; -fx-background-radius: 20;");
                button.toFront();
            }
        }
    }

    /**
     * Builds a default Toggle Switch.
     */
    public SwitchToggle() {
        bindProperties();
        setStyled();
        button.setOnAction((e) -> switchedOn.set(!switchedOn.get()));
        defaultLabel.setOnMouseClicked((e) -> switchedOn.set(!switchedOn.get()));
        getChildren().addAll(defaultLabel, button);
        switchedOn.addListener((a,b,c) -> {
            if (c) {
                defaultLabel.setText("ON");
                setStyle("-fx-background-color: #a7ef88; -fx-background-radius: 20;");
                defaultLabel.toFront();
                selected = true;
            } else {
                defaultLabel.setText("OFF");
                setStyle("-fx-background-color: #aeb0b2; -fx-background-radius: 20;");
                button.toFront();
                selected = false;
            }
        });
    }

    /**
     * Builds a customizable Toggle Switch.
     * Can set the text and background of both the true and
     * false state of the Toggle Switch. Can also set a
     * transitionType <code>TransitionType</code> for the
     * Toggle Switch when changing between the true and false states.
     * @param trueState is the text of the true state of the Toggle Switch.
     * @param trueColor is the background color of the true state of the Toggle Switch.
     * @param falseState is the text of the false state of the Toggle Switch.
     * @param falseColor is the background color of the false state of the Toggle Switch.
     * @param transitionType is the transitionType of the customizable Toggle Switch.
     */
    public SwitchToggle(String trueState, Color trueColor, String falseState, Color falseColor, TransitionType transitionType) {
        this.toggleSwitch = true;
        this.onState = trueState;
        this.offState = falseState;
        this.falseColored = falseColor;
        this.trueColored = trueColor;
        init(falseState);
        switchedOn.addListener((a,b,c) -> {
            if (c) {
                label.setText(trueState);
                setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(trueColor)));
                label.toFront();
                selected = true;
                setTransition(transitionType);
            } else {
                label.setText(falseState);
                setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(falseColor)));
                button.toFront();
                selected = false;
                setTransition(transitionType);
            }
        });
    }

    /**
     * Initializes the Toggle Switch.
     * @param startText is the text to be displayed for the default state of
     *      the Toggle Switch.
     */
    private void init(String startText) {
        text = new Text(String.format("  %s  ", startText));
        text.applyCss();
        button.setOnAction((e) -> switchedOn.set(!switchedOn.get()));
        label.setOnMouseClicked((e) -> switchedOn.set(!switchedOn.get()));
        getChildren().addAll(label, button);
        setStyled();
        bindProperties();
    }

    /**
     * Sets the style of the Toggle Switch.
     */
    private void setStyled() {
        if (toggleSwitch) {
            this.initWidth = (text.getLayoutBounds().getWidth() * 2);
            setWidth(initWidth);
            setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(falseColored)));
            setAlignment(Pos.CENTER_LEFT);
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
            button.setStyle("-fx-background-radius: 20;");
        } else {
            setWidth(80);
            setStyle("-fx-background-color: #aeb0b2; -fx-background-radius: 20;");
            setAlignment(Pos.CENTER_LEFT);
            defaultLabel.setAlignment(Pos.CENTER);
            defaultLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
            button.setStyle("-fx-background-radius: 20;");
        }
    }

    /**
     * Binds properties of the label and button within the Toggle Switch
     * to properties of the Toggle Switch height and width.
     */
    private void bindProperties() {
        if (toggleSwitch) {
            label.prefWidthProperty().bind(widthProperty().divide(2));
            label.prefHeightProperty().bind(heightProperty());
            button.prefWidthProperty().bind(widthProperty().divide(2));
            button.prefHeightProperty().bind(heightProperty());
        } else {
            defaultLabel.prefWidthProperty().bind(widthProperty().divide(2));
            defaultLabel.prefHeightProperty().bind(heightProperty());
            button.prefWidthProperty().bind(widthProperty().divide(2));
            button.prefHeightProperty().bind(heightProperty());
        }
    }

    /**
     * Determines the Hex representation of the inputted Color variable.
     * @param color is the inputted <code>Color</code>.
     */
    private static String colorToHex(Color color) {
        return colorChanelToHex(color.getRed()) + colorChanelToHex(color.getGreen()) +
                colorChanelToHex(color.getBlue()) + colorChanelToHex(color.getOpacity());
    }

    /**
     * Sets the inputted color numeric value to its String representation.
     * @param colorValue is the inputted Color variable in double format.
     */
    private static String colorChanelToHex (double colorValue) {
        String cal = Integer.toHexString((int) Math.min(Math.round(colorValue * 255), 255));
        if (cal.length() == 1) {cal = "0" + cal;}
        return cal;
    }

    /**
     * Sets the <code>TransitionType</code> of the Toggle Switch.
     * @param transitionType is the specified transitionType for a
     *                       customizable Toggle Switch.
     */
    private void setTransition(TransitionType transitionType) {
        if (transitionType == TransitionType.COMPRESS) {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(prefWidthProperty(), 50)),
                    new KeyFrame(Duration.millis(500.0d), new KeyValue(prefWidthProperty(), initWidth))
            );
            timeline.play();
        } else if (transitionType == TransitionType.POP) {
            button.setScaleX(0.8);
            button.setScaleY(0.8);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        button.setScaleX(1.0);
                        button.setScaleY(1.0);
                    });
                }
            }, 200);
        } else if (transitionType == TransitionType.ROTATE) {
            if (selected) {
                label.setText("**");
                label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
                label.setRotate(90);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            label.setRotate(180);
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Platform.runLater(() -> {
                                        label.setRotate(270);
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(() -> {
                                                    label.setRotate(360);
                                                    timer.schedule(new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            Platform.runLater(() -> {
                                                                label.setText(onState);
                                                                label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
                                                            });
                                                        }
                                                    }, 200);
                                                });
                                            }
                                        }, 100);
                                    });
                                }
                            }, 100);
                        });
                    }
                }, 100);
            } else {
                label.setText("**");
                label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
                label.setRotate(-270);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            label.setRotate(-180);
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Platform.runLater(() -> {
                                        label.setRotate(-90);
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(() -> {
                                                    label.setRotate(0);
                                                    timer.schedule(new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            Platform.runLater(() -> {
                                                                label.setText(offState);
                                                                label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
                                                            });
                                                        }
                                                    }, 200);
                                                });
                                            }
                                        }, 100);
                                    });
                                }
                            }, 100);
                        });
                    }
                }, 100);
            }
        } else if (transitionType == TransitionType.BUZZ) {
            button.setScaleX(0.8);
            button.setScaleY(0.8);
            if (selected) {
                button.setTranslateX(-2.0);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            button.setTranslateY(2.0);
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Platform.runLater(() -> {
                                        button.setTranslateX(2.0);
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(() -> {
                                                    button.setTranslateY(-2.0);
                                                    timer.schedule(new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            Platform.runLater(() -> {
                                                                button.setTranslateX(-2.0);
                                                                timer.schedule(new TimerTask() {
                                                                    @Override
                                                                    public void run() {
                                                                        Platform.runLater(() -> {
                                                                            button.setTranslateY(2.0);
                                                                            timer.schedule(new TimerTask() {
                                                                                @Override
                                                                                public void run() {
                                                                                    Platform.runLater(() -> {
                                                                                        button.setTranslateX(0.0);
                                                                                        button.setTranslateY(0.0);
                                                                                        button.setScaleX(1.0);
                                                                                        button.setScaleY(1.0);
                                                                                    });
                                                                                }
                                                                            }, 100);
                                                                        });
                                                                    }
                                                                }, 100);
                                                            });
                                                        }
                                                    }, 100);
                                                });
                                            }
                                        }, 100);
                                    });
                                }
                            }, 100);
                        });
                    }
                }, 100);
            } else {
                button.setTranslateX(2.0);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            button.setTranslateY(-2.0);
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Platform.runLater(() -> {
                                        button.setTranslateX(-2.0);
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(() -> {
                                                    button.setTranslateY(2.0);
                                                    timer.schedule(new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            Platform.runLater(() -> {
                                                                button.setTranslateX(2.0);
                                                                timer.schedule(new TimerTask() {
                                                                    @Override
                                                                    public void run() {
                                                                        Platform.runLater(() -> {
                                                                            button.setTranslateY(-2.0);
                                                                            timer.schedule(new TimerTask() {
                                                                                @Override
                                                                                public void run() {
                                                                                    Platform.runLater(() -> {
                                                                                        button.setTranslateX(0.0);
                                                                                        button.setTranslateY(0.0);
                                                                                        button.setScaleX(1.0);
                                                                                        button.setScaleY(1.0);
                                                                                    });
                                                                                }
                                                                            }, 100);
                                                                        });
                                                                    }
                                                                }, 100);
                                                            });
                                                        }
                                                    }, 100);
                                                });
                                            }
                                        }, 100);
                                    });
                                }
                            }, 100);
                        });
                    }
                }, 100);
            }
        } else if (transitionType == TransitionType.NONE) {}
    }

}
