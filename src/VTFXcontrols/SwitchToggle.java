/*
 * Copyright 2017-2022 VernTechnologies LLCs
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
  Created by Vern Technologies on 7/19/17. For package VTFXcontrols.
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
 * @version 1.0.0.3
 *
 */

public class SwitchToggle extends HBox {

    private final Label label = new Label();
    private final Button button = new Button();
    private final SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
    private boolean selected;
    private Double switchWidth;
    private String onState = "ON";
    private String offState = "OFF";
    private Color falseColored = Color.web("#aeb0b2");
    private Color trueColored = Color.web("#a7ef88");
    private Text textTrue;
    private Text textFalse;
    private TransitionType transType = TransitionType.NONE;
    private final Timer timer = new Timer();

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
        if (state) {
            label.setText(onState);
            setStyle(String.format("-fx-background-color: #%s ; -fx-background-radius: 20;", colorToHex(trueColored)));
            label.setPrefWidth(textTrue.getLayoutBounds().getWidth());
            button.setPrefWidth(textFalse.getLayoutBounds().getWidth());
            label.toFront();
        } else {
            label.setText(offState);
            setStyle(String.format("-fx-background-color: #%s ; -fx-background-radius: 20;", colorToHex(falseColored)));
            label.setPrefWidth(textFalse.getLayoutBounds().getWidth());
            button.setPrefWidth(textTrue.getLayoutBounds().getWidth());
            button.toFront();
        }
    }

    /**
     * Sets the true state of the Toggle Switch to the specified color.
     * @param trueColored contains the inputted color value.
     */
    public void setTrueColored(Color trueColored) {
        this.trueColored = trueColored;
        if (isSelected()) {
            setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(trueColored)));
        }
    }

    /**
     * Sets the false state of the Toggle Switch the specified color.
     * @param falseColored contains the inputted color value.
     */
    public void setFalseColored(Color falseColored) {
        this.falseColored = falseColored;
        if (!isSelected()) {
            setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(falseColored)));
        }
    }

    /**
     * Sets the switch color of the Toggle Switch to the specified color.
     * @param switchColor contains the inputted color value.
     */
    public void setSwitchColor(Color switchColor) {
        button.setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(switchColor)));
    }

    /**
     * Sets the Toggle Switch true state text to the specified String value.
     * @param trueText contains the inputted String value.
     */
    public void setTrueText(String trueText) {
        this.onState = trueText;
        initText(onState, offState);
        if (isSelected()) {
            label.setText(onState);
            setSelected(true);
            label.setPrefWidth(textTrue.getLayoutBounds().getWidth());
            button.setPrefWidth(textFalse.getLayoutBounds().getWidth());
        }
        this.switchWidth = (textTrue.getLayoutBounds().getWidth() + textFalse.getLayoutBounds().getWidth());
    }

    /**
     * Sets the Toggle Switch false state text to the specified String value.
     * @param falseText contains the inputted String value.
     */
    public void setFalseText(String falseText) {
        this.offState = falseText;
        initText(onState, offState);
        if (!isSelected()) {
            label.setText(offState);
            setSelected(false);
            label.setPrefWidth(textFalse.getLayoutBounds().getWidth());
            button.setPrefWidth(textTrue.getLayoutBounds().getWidth());
        }
        this.switchWidth = (textTrue.getLayoutBounds().getWidth() + textFalse.getLayoutBounds().getWidth());
    }

    /**
     * Sets the transition type of the Toggle Switch to the specified transition type.
     * @param transitionType contains the inputted TransitionType.
     */
    public void setTransitionType(TransitionType transitionType) {this.transType = transitionType;}

    /**
     * Builds a default Toggle Switch.
     */
    public SwitchToggle() {
        init(onState, offState);
        label.setText(offState);
        label.setPrefWidth(textFalse.getLayoutBounds().getWidth());
        button.setPrefWidth(textTrue.getLayoutBounds().getWidth());
        this.switchWidth = (textTrue.getLayoutBounds().getWidth() + textFalse.getLayoutBounds().getWidth());
        switchedOn.addListener((a,b,c) -> {
            if (c) {
                label.setText(onState);
                label.setPrefWidth(textTrue.getLayoutBounds().getWidth());
                button.setPrefWidth(textFalse.getLayoutBounds().getWidth());
                setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(trueColored)));
                label.toFront();
                selected = true;
                setTransition(transType);
            } else {
                label.setText(offState);
                label.setPrefWidth(textFalse.getLayoutBounds().getWidth());
                button.setPrefWidth(textTrue.getLayoutBounds().getWidth());
                setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(falseColored)));
                button.toFront();
                selected = false;
                setTransition(transType);
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
        this.onState = trueState;
        this.offState = falseState;
        this.falseColored = falseColor;
        this.trueColored = trueColor;
        this.transType = transitionType;
        init(onState, offState);
        label.setPrefWidth(textFalse.getLayoutBounds().getWidth());
        button.setPrefWidth(textTrue.getLayoutBounds().getWidth());
        this.switchWidth = (textTrue.getLayoutBounds().getWidth() + textFalse.getLayoutBounds().getWidth());
        switchedOn.addListener((a,b,c) -> {
            if (c) {
                label.setText(onState);
                label.setPrefWidth(textTrue.getLayoutBounds().getWidth());
                button.setPrefWidth(textFalse.getLayoutBounds().getWidth());
                setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(trueColored)));
                label.toFront();
                selected = true;
                setTransition(transType);
            } else {
                label.setText(offState);
                label.setPrefWidth(textFalse.getLayoutBounds().getWidth());
                button.setPrefWidth(textTrue.getLayoutBounds().getWidth());
                setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(falseColored)));
                button.toFront();
                selected = false;
                setTransition(transType);
            }
        });
    }

    /**
     * Initializes the Toggle Switch.
     * @param trueText is the text to be displayed for the default true state of the Toggle Switch.
     * @param falseText is the text to be displayed for the default false state of the Toggle Switch.
     */
    private void init(String trueText, String falseText) {
        textTrue = new Text(String.format("   %s   ", trueText));
        textTrue.applyCss();
        textFalse = new Text(String.format("   %s   ", falseText));
        textFalse.applyCss();
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
        setStyle(String.format("-fx-background-color: #%s; -fx-background-radius: 20;", colorToHex(falseColored)));
        setAlignment(Pos.CENTER_LEFT);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        button.setStyle("-fx-background-radius: 20;");
    }

    /**
     * Binds properties of the label and button within the Toggle Switch
     * to properties of the Toggle Switch height and width.
     */
    private void bindProperties() {
        label.prefHeightProperty().bind(heightProperty());
        button.prefHeightProperty().bind(heightProperty());
    }

    /**
     * Initializes the Text used to set the width of the Toggle Switch.
     * @param trueText is the text to be displayed for the true state of the Toggle Switch.
     * @param falseText is the text to be displayed for the false state of the Toggle Switch.
     */
    private void initText(String trueText, String falseText) {
        textTrue = new Text(String.format("   %s   ", trueText));
        textTrue.applyCss();
        textFalse = new Text(String.format("   %s   ", falseText));
        textFalse.applyCss();
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
        switch (transitionType) {
            case COMPRESS:
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(prefWidthProperty(), 50)),
                        new KeyFrame(Duration.millis(500.0d), new KeyValue(prefWidthProperty(), this.switchWidth))
                );
                timeline.play();
                break;
            case POP:
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
                break;
            case ROTATE:
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
                break;
            case BUZZ:
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
                break;
        }
    }

}
