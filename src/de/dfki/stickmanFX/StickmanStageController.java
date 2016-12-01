package de.dfki.stickmanFX;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.dfki.common.Gender;
import de.dfki.common.StickmansOnStage;
import de.dfki.common.commonFX3D.ViewController;
import de.dfki.stickmanFX.stage.StickmanStageFX;
import de.dfki.stickmanFX.stage.StageRoomFX;
import de.dfki.stickmanFX.stage.StickmansOnStageFX;
import de.dfki.stickmanSwing.StickmanSwing;
import de.dfki.stickmanFX.xmlsettings.StickmanDataFX;
import de.dfki.util.HandleColor;
import de.dfki.util.StickmanFillCombo;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Slider;


/**
 *
 * @author Robbie
 */
public class StickmanStageController implements ViewController {

    private static String packEmotionExpression = "de.dfki.stickmanFX.animation.facefx";
    private static String packGesture = "de.dfki.stickmanFX.animation.esturefx";
    private ArrayList<String> mStickmanComboList = new ArrayList<>();
    private StickmansOnStage mStickmanOnstage;
    private String mStickmancombobox = null;
    final private ToggleGroup groupPerlin = new ToggleGroup();
    final private ToggleGroup groupEnvironmentRadioButton = new ToggleGroup();
    private String backgroundRecord = null;
    private List<StickmanDataFX> mStickmanDataFX = new ArrayList<StickmanDataFX>();
//    private final static ObservableList<String> backgroundList = FXCollections.observableArrayList("office",
//	    "grassland");

    @FXML
    private Label Stickman;
    @FXML
    private Label BodyColour;
    @FXML
    private Label Environment;
    @FXML
    private Label EmotionExpression;
    @FXML
    private Label IdleSection;
    @FXML
    private Label Posture;
    @FXML
    private Label Background;
    @FXML
    private ColorPicker BackgroundColorPicker;
    @FXML
    private Label ShowEmotionName;

    @FXML
    private RadioButton FadeIn;
    @FXML
    private RadioButton FadeOut;
    @FXML
    private RadioButton GoDown;
    @FXML
    private RadioButton ComeUp;
    @FXML
    private RadioButton DisappearToSmall;
    @FXML
    private RadioButton ComeBackFromSmall;
    @FXML
    private RadioButton Speaking;

    @FXML
    private RadioButton CoverMouth;
    @FXML
    private RadioButton TouchHead;
    @FXML
    private RadioButton WaveLeft;
    @FXML
    private RadioButton WaveRight;

    @FXML
    private RadioButton WithPerlinNoise;
    @FXML
    private RadioButton WithoutPerlinNoise;

    @FXML
    private ComboBox<String> StickmanComboBox;
    @FXML
    private ComboBox<String> HeadComboBoxColor;
    @FXML
    private ColorPicker HeadColorPicker;
    @FXML
    private Slider HeadColorSlider;
    @FXML
    private ComboBox<String> HairComboBoxColor;
    @FXML
    private ColorPicker HairColorPicker;
    @FXML
    private Slider HairColorSlider;
    @FXML
    private ComboBox<String> BodyComboBoxColor;
    @FXML
    private ColorPicker BodyColorPicker;
    @FXML
    private Slider BodyColorSlider;
    @FXML
    private ComboBox<String> LimbsComboBoxColor;
    @FXML
    private ColorPicker LimbsColorPicker;
    @FXML
    private Slider LimbsColorSlider;
    @FXML
    private ComboBox<String> EmotionExpressionComboBox;
    @FXML
    private ComboBox<String> BackgroundComboBoxPic;

    @FXML
    private GridPane gridPaneControlStickman;
    @FXML
    private GridPane gridPaneControlColor;
    @FXML
    private GridPane gridPaneControlEmotion;
    @FXML
    private GridPane gridPaneControlIdleSection;
    @FXML
    private GridPane gridPaneControlEnvironment;
    @FXML
    private GridPane gridPaneControlPosture;
    @FXML
    private GridPane gridPaneControlBackground;
    @FXML
    private HBox StickmanFlowPane;

    @FXML
    private ScrollPane stickmanScrollPane;

    @FXML
    private Button RestButton;
    @FXML
    private Button ExitButton;
    @FXML
    private Button SaveButton;

    @FXML
    public void initialize() {
	initColorSlider();
	setIdForLabel();
	handleGroupForEnvironmentRadioButton();
	HeadComboBoxColor.getItems().addAll("Festucine", "Beige", "Blue", "Black", "Red");
	HairComboBoxColor.getItems().addAll("Saffron Yellow", "Brown", "Yellow", "Beige", "Blue", "Black", "Red",
		"Gold");
	BodyComboBoxColor.getItems().addAll("Purple", "Green", "Beige", "Black", "Yellow", "White");
	LimbsComboBoxColor.getItems().addAll("Black", "Yellow", "White");
	BackgroundComboBoxPic.getItems().addAll(StickmanFX.backgroundList);
	
	// Default show
	handleStickman();
	handleBodyColour();

	fillComboForEmotionExpression();
	// Select a stickmanSwing
	StickmanComboBox.setOnAction((event) -> {
	    mStickmancombobox = StickmanComboBox.getSelectionModel().getSelectedItem();
	    // set the setValue of combobox
	    setComboboxValue(getStickmanAsFx(mStickmancombobox));
	});
	
	// Show emotion
	EmotionExpressionComboBox.setOnAction((event) -> {
	    String mEmotion = EmotionExpressionComboBox.getSelectionModel().getSelectedItem();

	    if ((mEmotion != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    getStickmanAsFx(mStickmancombobox).doAnimation(mEmotion, 70, true);
		    EmotionExpressionComboBox.getSelectionModel().clearSelection();
		    ShowEmotionName.setText(mEmotion);
		});
	    }
	});

	// change bodyColor
	BodyComboBoxColor.setOnAction((event) -> {
	    String color = BodyComboBoxColor.getSelectionModel().getSelectedItem();
	    if ((color != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    if (getStickmanAsFx(mStickmancombobox).mType == Gender.TYPE.MALE) {
			getStickmanAsFx(mStickmancombobox).mBodyFX.mMaleColor = HandleColor.switchColor(color);
			getStickmanAsFx(mStickmancombobox).update();
		    } else {
			getStickmanAsFx(mStickmancombobox).mBodyFX.mFemaleColor = HandleColor.switchColor(color);
			getStickmanAsFx(mStickmancombobox).update();
		    }
		});
	    }

	});

	BodyColorPicker.setOnAction((event) -> {
	    Color bodyColor = BodyColorPicker.getValue();
	    if ((bodyColor != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    if (getStickmanAsFx(mStickmancombobox).mType == Gender.TYPE.MALE) {
			float mOpacityRecord = getStickmanAsFx(mStickmancombobox).mBodyFX.mColoropacity;
			getStickmanAsFx(mStickmancombobox).mBodyFX.mMaleColor = new Color(bodyColor.getRed(),
				bodyColor.getGreen(), bodyColor.getBlue(), mOpacityRecord);
			;
			getStickmanAsFx(mStickmancombobox).update();
		    } else {
			float mOpacityRecord = getStickmanAsFx(mStickmancombobox).mBodyFX.mColoropacity;
			getStickmanAsFx(mStickmancombobox).mBodyFX.mFemaleColor = new Color(bodyColor.getRed(),
				bodyColor.getGreen(), bodyColor.getBlue(), mOpacityRecord);
			;
			getStickmanAsFx(mStickmancombobox).update();
		    }
		});
	    }
	});

	BodyColorSlider.valueProperty().addListener(new ChangeListener<Number>() {
	    public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
		if (mStickmancombobox != null) {
		    Platform.runLater(() -> {
			if (getStickmanAsFx(mStickmancombobox).mType == Gender.TYPE.MALE) {
			    getStickmanAsFx(mStickmancombobox).mBodyFX.mColoropacity = new_val.floatValue();
			    Color mColorRecord = getStickmanAsFx(mStickmancombobox).mBodyFX.mMaleColor;
			    getStickmanAsFx(mStickmancombobox).mBodyFX.mMaleColor = new Color(mColorRecord.getRed(),
				    mColorRecord.getGreen(), mColorRecord.getBlue(), new_val.floatValue());
			    ;
			    getStickmanAsFx(mStickmancombobox).update();
			} else {
			    getStickmanAsFx(mStickmancombobox).mBodyFX.mColoropacity = new_val.floatValue();
			    Color mColorRecord = getStickmanAsFx(mStickmancombobox).mBodyFX.mFemaleColor;
			    getStickmanAsFx(mStickmancombobox).mBodyFX.mFemaleColor = new Color(mColorRecord.getRed(),
				    mColorRecord.getGreen(), mColorRecord.getBlue(), new_val.floatValue());
			    ;
			    getStickmanAsFx(mStickmancombobox).update();
			}
		    });
		}
	    }
	});

	// change hairColor
	HairComboBoxColor.setOnAction((event) -> {
	    String color = HairComboBoxColor.getSelectionModel().getSelectedItem();
	    if ((color != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    if (getStickmanAsFx(mStickmancombobox).mType == Gender.TYPE.MALE) {
			getStickmanAsFx(mStickmancombobox).mMaleHairFX.mColor = HandleColor.switchColor(color);
			getStickmanAsFx(mStickmancombobox).update();
		    } else {
			getStickmanAsFx(mStickmancombobox).mFemaleHairFX.mColor = HandleColor.switchColor(color);
			getStickmanAsFx(mStickmancombobox).update();
		    }
		});
	    }

	});
	
	HairColorPicker.setOnAction((event) -> {
	    Color hairColor = HairColorPicker.getValue();
	    if ((hairColor != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    if (getStickmanAsFx(mStickmancombobox).mType == Gender.TYPE.MALE) {
			float mOpacityRecord = getStickmanAsFx(mStickmancombobox).mMaleHairFX.mColoropacity;
			getStickmanAsFx(mStickmancombobox).mMaleHairFX.mColor = new Color(hairColor.getRed(),
				hairColor.getGreen(), hairColor.getBlue(), mOpacityRecord);
			;
			getStickmanAsFx(mStickmancombobox).update();
		    } else {
			float mOpacityRecord = getStickmanAsFx(mStickmancombobox).mFemaleHairFX.mColoropacity;
			getStickmanAsFx(mStickmancombobox).mFemaleHairFX.mColor = new Color(hairColor.getRed(),
				hairColor.getGreen(), hairColor.getBlue(), mOpacityRecord);
			;
			getStickmanAsFx(mStickmancombobox).update();
		    }
		});
	    }
	});

	HairColorSlider.valueProperty().addListener(new ChangeListener<Number>() {
	    public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
		if (mStickmancombobox != null) {
		    Platform.runLater(() -> {
			if (getStickmanAsFx(mStickmancombobox).mType == Gender.TYPE.MALE) {
			    getStickmanAsFx(mStickmancombobox).mMaleHairFX.mColoropacity = new_val.floatValue();
			    Color mColorRecord = getStickmanAsFx(mStickmancombobox).mMaleHairFX.mColor;
			    getStickmanAsFx(mStickmancombobox).mMaleHairFX.mColor = new Color(mColorRecord.getRed(),
				    mColorRecord.getGreen(), mColorRecord.getBlue(), new_val.floatValue());
			    ;
			    getStickmanAsFx(mStickmancombobox).update();
			} else {
			    getStickmanAsFx(mStickmancombobox).mFemaleHairFX.mColoropacity = new_val.floatValue();
			    Color mColorRecord = getStickmanAsFx(mStickmancombobox).mFemaleHairFX.mColor;
			    getStickmanAsFx(mStickmancombobox).mFemaleHairFX.mColor = new Color(mColorRecord.getRed(),
				    mColorRecord.getGreen(), mColorRecord.getBlue(), new_val.floatValue());
			    ;
			    getStickmanAsFx(mStickmancombobox).update();
			}
		    });
		}
	    }
	});

	
	// change head Color
	HeadComboBoxColor.setOnAction((event) -> {
	    String color = HeadComboBoxColor.getSelectionModel().getSelectedItem();
	    if ((color != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    getStickmanAsFx(mStickmancombobox).mHeadFX.mColor = HandleColor.switchColor(color);
		    if (getStickmanAsFx(mStickmancombobox).mHeadFX.mColor != null)
			getStickmanAsFx(mStickmancombobox).update();
		});
	    }
	});

	HeadColorPicker.setOnAction((event) -> {
	    Color headColor = HeadColorPicker.getValue();
	    if ((headColor != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    // getStickmanAsFx(mStickmancombobox).mHeadFX.mColor =
		    // headColor;
		    float mOpacityRecord = getStickmanAsFx(mStickmancombobox).mHeadFX.mColoropacity;
		    getStickmanAsFx(mStickmancombobox).mHeadFX.mColor = new Color(headColor.getRed(),
			    headColor.getGreen(), headColor.getBlue(), mOpacityRecord);
		    if (getStickmanAsFx(mStickmancombobox).mHeadFX.mColor != null)
			getStickmanAsFx(mStickmancombobox).update();
		});
	    }
	});

	HeadColorSlider.valueProperty().addListener(new ChangeListener<Number>() {
	    public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
		if (mStickmancombobox != null) {
		    Platform.runLater(() -> {
			getStickmanAsFx(mStickmancombobox).mHeadFX.mColoropacity = new_val.floatValue();
			Color mColorRecord = getStickmanAsFx(mStickmancombobox).mHeadFX.mColor;
			getStickmanAsFx(mStickmancombobox).mHeadFX.mColor = new Color(mColorRecord.getRed(),
				mColorRecord.getGreen(), mColorRecord.getBlue(), new_val.floatValue());
			getStickmanAsFx(mStickmancombobox).update();
		    });
		}
	    }
	});

	
	// change background PICTURE
	BackgroundComboBoxPic.setOnAction((event) -> {
	    String pic = BackgroundComboBoxPic.getSelectionModel().getSelectedItem();
	    if (pic != null) {
		if (StickmanFX.backgroundList.contains(pic)) {
		    Platform.runLater(() -> {
			try {
			    HBox mStickmanPane = ((StickmanStageFX) mStickmanOnstage.getStageStickman())
				    .getStickmanPane((((StageRoomFX) mStickmanOnstage
					    .getStageRoom()).CONFIG_STAGE));
			    mStickmanPane.setStyle("-fx-background-image: url('/de/dfki/stickmanFX/image/" + pic
				    + ".jpg');"
				    + "-fx-background-repeat: repeat;-fx-background-position: center center; -fx-background-size: contain;");
			    backgroundRecord = pic;
			} catch (Exception e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		    });
		}
	    }
	});
	
	// change background COLOUR
	BackgroundColorPicker.setOnAction((event) -> {
	    Color backgroundColor = BackgroundColorPicker.getValue();
		    Platform.runLater(() -> {
			try {
			    HBox mStickmanPane = ((StickmanStageFX) mStickmanOnstage.getStageStickman())
				    .getStickmanPane((((StageRoomFX) mStickmanOnstage
					    .getStageRoom()).CONFIG_STAGE));
			    String hex = toHexCode(backgroundColor);
		            mStickmanPane.setStyle("-fx-background-color: " + hex + ";");
		            backgroundRecord = hex;
			} catch (Exception e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		    });
	});

	// change limbs Color
	LimbsComboBoxColor.setOnAction((event) -> {
	    String color = LimbsComboBoxColor.getSelectionModel().getSelectedItem();
	    if ((color != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    // mStickmanOnstage.getStickman(mStickmancombobox).mLeftLegFX.mColor
		    // = switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mLeftUpperLegFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mLeftForeLegFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mLeftFootFX.mColor = HandleColor.switchColor(color);
		    // mStickmanOnstage.getStickman(mStickmancombobox).mRightLegFX.mColor
		    // = switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mRightUpperLegFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mRightForeLegFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mRightFootFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mLeftHandFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mRightHandFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mLeftShoulderFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mRightShoulderFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mLeftUpperArmFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mLeftForeArmFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mRightUpperArmFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mRightForeArmFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).mNeckFX.mColor = HandleColor.switchColor(color);
		    getStickmanAsFx(mStickmancombobox).update();
		});
	    }
	});
	
	LimbsColorPicker.setOnAction((event) -> {
	    Color limbsColor = LimbsColorPicker.getValue();
	    if ((limbsColor != null) && (mStickmancombobox != null)) {
		Platform.runLater(() -> {
		    float mOpacityRecord = getStickmanAsFx(mStickmancombobox).mLeftUpperLegFX.mColoropacity;
		    Color mColorChange = new Color(limbsColor.getRed(), limbsColor.getGreen(), limbsColor.getBlue(),
			    mOpacityRecord);
		    getStickmanAsFx(mStickmancombobox).mLeftUpperLegFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mLeftForeLegFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mLeftFootFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mRightUpperLegFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mRightForeLegFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mRightFootFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mLeftHandFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mRightHandFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mLeftShoulderFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mRightShoulderFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mLeftUpperArmFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mLeftForeArmFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mRightUpperArmFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mRightForeArmFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).mNeckFX.mColor = mColorChange;
		    getStickmanAsFx(mStickmancombobox).update();
		});
	    }
	});

	LimbsColorSlider.valueProperty().addListener(new ChangeListener<Number>() {
	    public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
		if (mStickmancombobox != null) {
		    Platform.runLater(() -> {

			getStickmanAsFx(mStickmancombobox).mLeftUpperLegFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mLeftForeLegFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mLeftFootFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mRightUpperLegFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mRightForeLegFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mRightFootFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mLeftHandFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mRightHandFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mLeftShoulderFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mRightShoulderFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mLeftUpperArmFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mLeftForeArmFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mRightUpperArmFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mRightForeArmFX.mColoropacity = new_val.floatValue();
			getStickmanAsFx(mStickmancombobox).mNeckFX.mColoropacity = new_val.floatValue();

			Color mColorRecord = getStickmanAsFx(mStickmancombobox).mLeftUpperLegFX.mColor;
			Color mColorChange = new Color(mColorRecord.getRed(), mColorRecord.getGreen(),
				mColorRecord.getBlue(), new_val.floatValue());
			getStickmanAsFx(mStickmancombobox).mLeftUpperLegFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mLeftForeLegFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mLeftFootFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mRightUpperLegFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mRightForeLegFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mRightFootFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mLeftHandFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mRightHandFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mLeftShoulderFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mRightShoulderFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mLeftUpperArmFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mLeftForeArmFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mRightUpperArmFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mRightForeArmFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).mNeckFX.mColor = mColorChange;
			getStickmanAsFx(mStickmancombobox).update();
		    });
		}
	    }
	});


	// set the color to default value
	RestButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		if ((mStickmancombobox != null)) {
		    Platform.runLater(() -> {
			if (getStickmanAsFx(mStickmancombobox).mType == Gender.TYPE.MALE) {
			    HeadComboBoxColor.setValue("Festucine");
			    HairComboBoxColor.setValue("Brown");
			    BodyComboBoxColor.setValue("Green");
			    LimbsComboBoxColor.setValue("Black");
			} else {
			    HeadComboBoxColor.setValue("Festucine");
			    HairComboBoxColor.setValue("Saffron Yellow");
			    BodyComboBoxColor.setValue("Purple");
			    LimbsComboBoxColor.setValue("Black");
			}
		    });

		    if ((groupEnvironmentRadioButton.getSelectedToggle() != null)) {
			String action = groupEnvironmentRadioButton.getSelectedToggle().getUserData().toString();
			Platform.runLater(() -> {
			    switch (action) {
			    case "FadeOut":
				getStickmanAsFx(mStickmancombobox).doAnimation("FadeIn", 1000, true);
				break;
			    case "GoDown":
				getStickmanAsFx(mStickmancombobox).doAnimation("ComeUp", 1000, true);
				break;
			    case "DisappearToSmall":
				getStickmanAsFx(mStickmancombobox).doAnimation("ComeBackFromSmall", 1000, true);
				break;
			    default:
				break;
			    }
			});
		    }
		    
		    setBackgroundFunction();
		}

		EnvironmentRadioButtonNotSelected();
		WithPerlinNoise.setSelected(true);
	    }
	});

	ExitButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		mStickmanOnstage.clearStage();
		((StickmanStageFX) mStickmanOnstage.getStageStickman()).clearStage(
			((StageRoomFX) mStickmanOnstage.getStageRoom()).CONFIG_STAGE);
	    }
	});

	saveToXml();
	handlePerlinNoise();
    }
    
    private void initColorSlider() {
	HeadColorSlider.setMin(0);
	HeadColorSlider.setMax(1);
	HeadColorSlider.setValue(1);
	HeadColorSlider.setShowTickLabels(true);
	HeadColorSlider.setShowTickMarks(true);
	HeadColorSlider.setMajorTickUnit(0.2);
	HeadColorSlider.setMinorTickCount(5);
	HeadColorSlider.setBlockIncrement(0.01);

	HairColorSlider.setMin(0);
	HairColorSlider.setMax(1);
	HairColorSlider.setValue(1);
	HairColorSlider.setShowTickLabels(true);
	HairColorSlider.setShowTickMarks(true);
	HairColorSlider.setMajorTickUnit(0.2);
	HairColorSlider.setMinorTickCount(5);
	HairColorSlider.setBlockIncrement(0.01);

	BodyColorSlider.setMin(0);
	BodyColorSlider.setMax(1);
	BodyColorSlider.setValue(1);
	BodyColorSlider.setShowTickLabels(true);
	BodyColorSlider.setShowTickMarks(true);
	BodyColorSlider.setMajorTickUnit(0.2);
	BodyColorSlider.setMinorTickCount(5);
	BodyColorSlider.setBlockIncrement(0.01);

	LimbsColorSlider.setMin(0);
	LimbsColorSlider.setMax(1);
	LimbsColorSlider.setValue(1);
	LimbsColorSlider.setShowTickLabels(true);
	LimbsColorSlider.setShowTickMarks(true);
	LimbsColorSlider.setMajorTickUnit(0.2);
	LimbsColorSlider.setMinorTickCount(5);
	LimbsColorSlider.setBlockIncrement(0.01);
    }

    public StickmanFX getStickmanAsFx(String mStickmancombobox) {
	return (StickmanFX) mStickmanOnstage.getStickman(mStickmancombobox);
    }

    public void setStickamnOnStage(StickmansOnStage commonStickmansOnStage) {
	this.mStickmanOnstage = commonStickmansOnStage;
//	fillComboForStickman();
    }

    private void fillComboForEmotionExpression() {
	ArrayList<String> getClassesNames;
	StickmanFillCombo mStickmanFillCombo = new StickmanFillCombo(packEmotionExpression);
	getClassesNames = mStickmanFillCombo.getComboList();
	ObservableList<String> classNames = FXCollections.observableArrayList();
	classNames.addAll(getClassesNames.stream().collect(Collectors.toList()));
	EmotionExpressionComboBox.getItems().addAll(classNames);
    }

    public void fillComboForStickman() {
	ObservableList<String> stickmanNames = FXCollections.observableArrayList();
	stickmanNames.addAll(mStickmanOnstage.getStickmanNames().stream().collect(Collectors.toList()));
	StickmanComboBox.getItems().clear();
	StickmanComboBox.getItems().addAll(stickmanNames);
	mStickmanComboList.clear();
	mStickmanComboList.addAll(stickmanNames);
	if(!stickmanNames.isEmpty()){
	    mStickmancombobox = stickmanNames.get(0);
	}
	StickmanComboBox.setValue(mStickmancombobox);	
	setComboboxValue(getStickmanAsFx(mStickmancombobox));
	
	setBackgroundFunction();
    }

    private void setBackgroundFunction() {
	this.backgroundRecord = getStickmanAsFx(mStickmancombobox).backgroundRecord;
	if (this.backgroundRecord != null) {
	    HBox mStickmanPane;
	    try {
		mStickmanPane = ((StickmanStageFX) mStickmanOnstage.getStageStickman())
			    .getStickmanPane((((StageRoomFX) mStickmanOnstage
				    .getStageRoom()).CONFIG_STAGE));
		// Upload the picture
		if (StickmanFX.backgroundList.contains(this.backgroundRecord)) {
		    mStickmanPane.setStyle("-fx-background-image: url('/de/dfki/stickmanFX/image/"
			    + this.backgroundRecord + ".jpg');"
			    + "-fx-background-repeat: repeat;-fx-background-position: center center; -fx-background-size: contain;");
		} else {
		    // change the color of the background
		    mStickmanPane.setStyle("-fx-background-color: " + this.backgroundRecord + ";");
		}
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    // set the setValue of combobox
    private void setComboboxValue(StickmanFX mStick) {
	if (mStick.mType == Gender.TYPE.MALE) {
	    String sBodyComboBoxColor = HandleColor.switchColorToString(mStick.mBodyFX.mMaleColor);
	    if (sBodyComboBoxColor != null)
		BodyComboBoxColor.setValue(sBodyComboBoxColor);
	} else {
	    String sBodyComboBoxColor = HandleColor.switchColorToString(mStick.mBodyFX.mFemaleColor);
	    if (sBodyComboBoxColor != null)
		BodyComboBoxColor.setValue(sBodyComboBoxColor);
	}

	if (mStick.mType == Gender.TYPE.MALE) {
	    String sHairComboBoxColor = HandleColor.switchColorToString(mStick.mMaleHairFX.mColor);
	    if (sHairComboBoxColor != null)
		HairComboBoxColor.setValue(sHairComboBoxColor);
	} else {
	    String sHairComboBoxColor = HandleColor.switchColorToString(mStick.mFemaleHairFX.mColor);
	    if (sHairComboBoxColor != null)
		HairComboBoxColor.setValue(sHairComboBoxColor);

	}

	String sHeadComboBoxColor = HandleColor.switchColorToString(mStick.mHeadFX.mColor);
	if (sHeadComboBoxColor != null)
	    HeadComboBoxColor.setValue(sHeadComboBoxColor);

	String sLimbsComboBoxColor = HandleColor.switchColorToString(mStick.mLeftUpperLegFX.mColor);
	if (sLimbsComboBoxColor != null)
	    LimbsComboBoxColor.setValue(sLimbsComboBoxColor);
    }

    @FXML
    private void handleStickman() {
	gridPaneControlStickman.setVisible(true);
	gridPaneControlColor.setVisible(false);
	gridPaneControlEmotion.setVisible(false);
	gridPaneControlIdleSection.setVisible(false);
	gridPaneControlEnvironment.setVisible(false);
	gridPaneControlPosture.setVisible(false);
	gridPaneControlBackground.setVisible(false);
    }

    @FXML
    private void handleBodyColour() {
	gridPaneControlStickman.setVisible(false);
	gridPaneControlColor.setVisible(true);
	gridPaneControlEmotion.setVisible(false);
	gridPaneControlIdleSection.setVisible(false);
	gridPaneControlEnvironment.setVisible(false);
	gridPaneControlPosture.setVisible(false);
	gridPaneControlBackground.setVisible(false);
    }

    @FXML
    private void handleEmotionExpression() {
	gridPaneControlStickman.setVisible(false);
	gridPaneControlColor.setVisible(false);
	gridPaneControlEmotion.setVisible(true);
	gridPaneControlIdleSection.setVisible(false);
	gridPaneControlEnvironment.setVisible(false);
	gridPaneControlPosture.setVisible(false);
	gridPaneControlBackground.setVisible(false);
	ShowEmotionName.setText("");
    }

    @FXML
    private void handleIdleSection() {
	gridPaneControlStickman.setVisible(false);
	gridPaneControlColor.setVisible(false);
	gridPaneControlEmotion.setVisible(false);
	gridPaneControlIdleSection.setVisible(true);
	gridPaneControlEnvironment.setVisible(false);
	gridPaneControlPosture.setVisible(false);
	gridPaneControlBackground.setVisible(false);
    }

    @FXML
    private void handleEnvironment() {
	gridPaneControlStickman.setVisible(false);
	gridPaneControlColor.setVisible(false);
	gridPaneControlEmotion.setVisible(false);
	gridPaneControlIdleSection.setVisible(false);
	gridPaneControlEnvironment.setVisible(true);
	gridPaneControlPosture.setVisible(false);
	gridPaneControlBackground.setVisible(false);
    }

    @FXML
    private void handlePosture() {
	gridPaneControlStickman.setVisible(false);
	gridPaneControlColor.setVisible(false);
	gridPaneControlEmotion.setVisible(false);
	gridPaneControlIdleSection.setVisible(false);
	gridPaneControlEnvironment.setVisible(false);
	gridPaneControlPosture.setVisible(true);
	gridPaneControlBackground.setVisible(false);
    }

    @FXML
    private void handleBackground() {
	gridPaneControlStickman.setVisible(false);
	gridPaneControlColor.setVisible(false);
	gridPaneControlEmotion.setVisible(false);
	gridPaneControlIdleSection.setVisible(false);
	gridPaneControlEnvironment.setVisible(false);
	gridPaneControlPosture.setVisible(false);
	gridPaneControlBackground.setVisible(true);
    }

    private void setIdForLabel() {
	BodyColour.setId("Menu");
	Environment.setId("Menu");
	EmotionExpression.setId("Menu");
	IdleSection.setId("Menu");
	Posture.setId("Menu");
	Stickman.setId("Menu");
	Background.setId("Menu");
    }

    private void handlePerlinNoise() {
	WithPerlinNoise.setUserData("With Perlin Noise");
	WithoutPerlinNoise.setUserData("Without Perlin Noise");
	WithPerlinNoise.setToggleGroup(groupPerlin);
	WithoutPerlinNoise.setToggleGroup(groupPerlin);

	groupPerlin.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	    public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
		if ((groupPerlin.getSelectedToggle() != null)
			&& ((null != mStickmanComboList) && (!mStickmanComboList.isEmpty()))) {
		    if (groupPerlin.getSelectedToggle().getUserData().toString() == "With Perlin Noise") {
			Platform.runLater(() -> {
			    for (String key : mStickmanComboList)
				getStickmanAsFx(key).doAnimation("StartIdle", 1000, true);
			});
		    } else {
			Platform.runLater(() -> {
			    for (String key : mStickmanComboList)
				getStickmanAsFx(key).doAnimation("StopIdle", 1000, true);
			});
		    }
		}
	    }
	});
    }

    public void setlePerlinNoiseOn() {
	WithPerlinNoise.setSelected(true);
	WithoutPerlinNoise.setSelected(false);
    }

    public void setlePerlinNoiseOff() {
	WithPerlinNoise.setSelected(false);
	WithoutPerlinNoise.setSelected(true);

    }

    private void handleGroupForEnvironmentRadioButton() {
	FadeIn.setUserData("FadeIn");
	FadeOut.setUserData("FadeOut");
	GoDown.setUserData("GoDown");
	ComeUp.setUserData("ComeUp");
	DisappearToSmall.setUserData("DisappearToSmall");
	ComeBackFromSmall.setUserData("ComeBackFromSmall");
	Speaking.setUserData("Speaking");

	FadeIn.setToggleGroup(groupEnvironmentRadioButton);
	FadeOut.setToggleGroup(groupEnvironmentRadioButton);
	GoDown.setToggleGroup(groupEnvironmentRadioButton);
	ComeUp.setToggleGroup(groupEnvironmentRadioButton);
	DisappearToSmall.setToggleGroup(groupEnvironmentRadioButton);
	ComeBackFromSmall.setToggleGroup(groupEnvironmentRadioButton);
	Speaking.setToggleGroup(groupEnvironmentRadioButton);

	groupEnvironmentRadioButton.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	    public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
		if ((groupEnvironmentRadioButton.getSelectedToggle() != null) && (mStickmancombobox != null)) {
		    String action = groupEnvironmentRadioButton.getSelectedToggle().getUserData().toString();
		    if (!action.equals("Speaking")) {
			Platform.runLater(() -> {
			    getStickmanAsFx(mStickmancombobox).doAnimation(action, 1000, true);
			});
		    } else {
			Platform.runLater(() -> {
			    getStickmanAsFx(mStickmancombobox).doAnimation(action, 3000,
				    "Stell Dir vor, Du kommst nach Hause, und ein Pferd steht in der Küche.", false);
			    Speaking.setSelected(false);
			});
		    }
		}

		if ((groupEnvironmentRadioButton.getSelectedToggle() != null) && (mStickmancombobox == null)) {
		    String action = groupEnvironmentRadioButton.getSelectedToggle().getUserData().toString();
		    Platform.runLater(() -> {
			groupEnvironmentRadioButton.getSelectedToggle().setSelected(false);
		    });
		}
	    }
	});
    }

    private void EnvironmentRadioButtonNotSelected() {
	FadeIn.setSelected(false);
	FadeOut.setSelected(false);
	GoDown.setSelected(false);
	ComeUp.setSelected(false);
	DisappearToSmall.setSelected(false);
	ComeBackFromSmall.setSelected(false);
	Speaking.setSelected(false);
    }

    private void saveToXml() {
	SaveButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		if (((null != mStickmanComboList) && (!mStickmanComboList.isEmpty()))) {
		    Platform.runLater(() -> {
			mStickmanDataFX.clear();
			for (String key : mStickmanComboList) {
			    String name = key;
			    String bodyColor;
			    String hairColor;
			    StickmanFX mStick = getStickmanAsFx(key);
			    if (mStick.mType == Gender.TYPE.MALE) {
				bodyColor = HandleColor.switchColorToString(mStick.mBodyFX.mMaleColor);
			    } else {
				bodyColor = HandleColor.switchColorToString(mStick.mBodyFX.mFemaleColor);
			    }

			    if (mStick.mType == Gender.TYPE.MALE) {
				hairColor = HandleColor.switchColorToString(mStick.mMaleHairFX.mColor);
			    } else {
				hairColor = HandleColor.switchColorToString(mStick.mFemaleHairFX.mColor);
			    }

			    String headColor = HandleColor.switchColorToString(mStick.mHeadFX.mColor);
			    String limbsColor = HandleColor.switchColorToString(mStick.mLeftUpperLegFX.mColor);

			    mStickmanDataFX.add(new StickmanDataFX(name, hairColor, headColor, bodyColor, limbsColor, backgroundRecord));
			}
			((StickmansOnStageFX) mStickmanOnstage).getmXmlTransform()
				.loadStickmanDataFXList(mStickmanDataFX);
			// StickmanOnstage.getmXmlTransform().loadStickmanDataFXList(mStickmanDataFX);
			handleSave();
		    });
		}
	    }
	});
    }

    private void handleSave() {

	File filexml = null;
	if (mStickmanOnstage.getmFilePath() != null)
	    filexml = new File(
		    mStickmanOnstage.getmFilePath() + File.separator + "stickmanSwing" + File.separator + "stickmanSwing.xml");
	else {
	    try {
		filexml = new File(new File(".").getCanonicalPath() + File.separator + "stickmanSwing" + File.separator
			+ "stickmanSwing.xml");
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	if (!filexml.exists())
	    filexml.getParentFile().mkdir();

	// Make sure it has the correct extension
	if (!filexml.getPath().endsWith(".xml")) {
	    filexml = new File(filexml.getPath() + ".xml");
	}
	((StickmansOnStageFX) mStickmanOnstage).getmXmlTransform().saveStickmanDataToFile(filexml);
    }
    
  //convert color to hex
    private String toHexCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}