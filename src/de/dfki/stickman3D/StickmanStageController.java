package de.dfki.stickman3D;

import java.awt.Point;
import java.util.ArrayList;
import java.util.stream.Collectors;

import de.dfki.stickman3D.controllerhelper.ColorHelper;
import de.dfki.stickman3D.controllerhelper.OpacityHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author Robbie
 */
public class StickmanStageController {

	private static String packEmotionExpression = "de.dfki.stickman3D.animation.face";
	private static String packGesture = "de.dfki.stickman3D.animation.gesture";
	private static String packHead = "de.dfki.stickman3D.animation.head";
	private static String packEnvironment = "de.dfki.stickman3D.animation.environment";
	private static String packPosture = "de.dfki.stickman3D.animation.posture";

	private StickmanStage mStickmanstage;
	private String mStickmancombobox = null;

	@FXML
	private RadioButton WithPerlinNoise;
	@FXML
	private RadioButton WithoutPerlinNoise;
	private ToggleGroup perlinNoiseGroup;

	@FXML
	private ComboBox<String> StickmanComboBox;
	@FXML
	private ComboBox<String> EmotionExpressionComboBox;

	@FXML
	private HBox StickmanFlowPane;

	@FXML
	private Button RestButton;
	@FXML
	private Button ExitButton;

	@FXML
	Button startCamera;
	@FXML
	Button stopCamera;
	@FXML
	Button resetCamera;
	public static boolean isCameraStarted = false;

	@FXML
	private ColorPicker headColorPicker;
	@FXML
	private ColorPicker hairColorPicker;
	@FXML
	private ColorPicker bodyColorPicker;
	@FXML
	private ColorPicker limbsColorPicker;
	@FXML
	private ColorPicker shoesColorPicker;
	@FXML
	private ColorPicker lipsColorPicker;
	@FXML
	private ColorPicker eyeColorPicker;
	@FXML
	private ColorPicker browColorPicker;
	@FXML
	private ColorPicker noseColorPicker;

	@FXML
	ScrollPane emotionsScrollPane;
	@FXML
	ScrollPane gestureScrollPane;
	@FXML
	ScrollPane headScrollPane;
	@FXML
	ScrollPane environmentScrollPane;
	@FXML
	ScrollPane postureScrollPane;

	@FXML
	private Button headColorReset;
	@FXML
	private Button headColorBrighter;
	@FXML
	private Button headColorDarker;
	@FXML
	private Button hairColorReset;
	@FXML
	private Button hairColorBrighter;
	@FXML
	private Button hairColorDarker;
	@FXML
	private Button bodyColorReset;
	@FXML
	private Button bodyColorBrighter;
	@FXML
	private Button bodyColorDarker;
	@FXML
	private Button limbsColorReset;
	@FXML
	private Button limbsColorBrighter;
	@FXML
	private Button limbsColorDarker;
	@FXML
	private Button shoesColorReset;
	@FXML
	private Button shoesColorBrighter;
	@FXML
	private Button shoesColorDarker;
	@FXML
	private Button lipsColorReset;
	@FXML
	private Button lipsColorBrighter;
	@FXML
	private Button lipsColorDarker;
	@FXML
	private Button eyeColorReset;
	@FXML
	private Button eyeColorBrighter;
	@FXML
	private Button eyeColorDarker;
	@FXML
	private Button browColorReset;
	@FXML
	private Button browColorBrighter;
	@FXML
	private Button browColorDarker;
	@FXML
	private Button noseColorReset;
	@FXML
	private Button noseColorBrighter;
	@FXML
	private Button noseColorDarker;
	@FXML
	private Slider cameraXSlider;
	@FXML
	private Slider cameraYSlider;
	@FXML
	private Slider cameraZSlider;
	@FXML
	private Button cameraXPlusTranslationButton;
	@FXML
	private Button cameraXMinusTranslationButton;
	@FXML
	private TextField cameraXTranslationField;
	@FXML
	private Button cameraYPlusTranslationButton;
	@FXML
	private Button cameraYMinusTranslationButton;
	@FXML
	private TextField cameraYTranslationField;
	@FXML
	private Button cameraZPlusTranslationButton;
	@FXML
	private Button cameraZMinusTranslationButton;
	@FXML
	private TextField cameraZTranslationField;
	@FXML
	private Button nearClipMinusButton;
	@FXML
	private Button nearClipPlusButton;
	@FXML
	private TextField nearClipField;
	@FXML
	private Button farClipMinusButton;
	@FXML
	private Button farClipPlusButton;
	@FXML
	private TextField farClipField;
	@FXML
	private Button fieldOfViewMinusButton;
	@FXML
	private Button fieldOfViewPlusButton;
	@FXML
	private TextField fieldOfViewField;
	@FXML
	private Slider headOpacitySlider;
	@FXML
	private Slider hairOpacitySlider;
	@FXML
	private Slider bodyOpacitySlider;
	@FXML
	private Slider limbsOpacitySlider;
	@FXML
	private Slider shoesOpacitySlider;
	@FXML
	private Slider lipsOpacitySlider;
	@FXML
	private Slider eyeOpacitySlider;
	@FXML
	private Slider browOpacitySlider;
	@FXML
	private Slider noseOpacitySlider;

	@FXML
	private ImageView bg1;
	@FXML
	private ImageView bg2;
	@FXML
	private ImageView bg3;
	@FXML
	private ImageView bg4;
	@FXML
	private ImageView bg5;
	@FXML
	private ImageView bg6;

	double xRotateFactor;
	double yRotateFactor;
	double zRotateFactor;

	public Stickman3D currentStickman;
	public static RadioButton currentRadioButton;

	@FXML
	public void initialize() {
		// Select a stickman
		StickmanComboBox.setOnAction((event) -> {
			mStickmancombobox = StickmanComboBox.getSelectionModel().getSelectedItem();
			currentStickman = StickmanStage.getStickmanFX(mStickmancombobox);
		});

		fillEmotionScrollPane();
		fillGestureScrollPane();
		fillHeadScrollPane();
		fillEnvironmentScrollPane();
		fillPostureScrollPane();

		perlinNoiseGroup = new ToggleGroup();
		WithPerlinNoise.setToggleGroup(perlinNoiseGroup);
		WithoutPerlinNoise.setToggleGroup(perlinNoiseGroup);
		WithoutPerlinNoise.getStylesheets().add(this.getClass().getResource("RadioButtonCSS.css").toExternalForm());
		WithPerlinNoise.getStylesheets().add(this.getClass().getResource("RadioButtonCSS.css").toExternalForm());

		cameraXSlider.setMin(-180);
		cameraXSlider.setMax(180);
		cameraXSlider.setValue(0);
		cameraXSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				if (isCameraStarted) {
					double newValue = new_val.doubleValue();
					double oldValue = old_val.doubleValue();
					xRotateFactor = newValue - oldValue;
					Point pivot = currentStickman.mUpperBody.getUpperBodyPosition();
					Rotate rx = new Rotate(xRotateFactor, pivot.x, pivot.y, 1505, Rotate.X_AXIS);
					StickmanStage.sCamera.getTransforms().addAll(rx);
				}
			}
		});
		
		cameraYSlider.setMin(-180);
		cameraYSlider.setMax(180);
		cameraYSlider.setValue(0);
		cameraYSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				if (isCameraStarted) {
					double newValue = new_val.doubleValue();
					double oldValue = old_val.doubleValue();
					yRotateFactor = newValue - oldValue;
					Point pivot = currentStickman.mUpperBody.getUpperBodyPosition();
					Rotate ry = new Rotate(yRotateFactor, pivot.x, pivot.y, 1505, Rotate.Y_AXIS);
					StickmanStage.sCamera.getTransforms().addAll(ry);
				}
			}
		});

		cameraZSlider.setMin(-180);
		cameraZSlider.setMax(180);
		cameraZSlider.setValue(0);
		cameraZSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				if (isCameraStarted) {
					double newValue = new_val.doubleValue();
					double oldValue = old_val.doubleValue();
					zRotateFactor = newValue - oldValue;
					Point pivot = currentStickman.mUpperBody.getUpperBodyPosition();
					Rotate rz = new Rotate(zRotateFactor, pivot.x, pivot.y, 1505, Rotate.Z_AXIS);
					StickmanStage.sCamera.getTransforms().addAll(rz);
				}
			}
		});

		OpacityHelper.headOpacityChanger(this, headOpacitySlider);
		OpacityHelper.hairOpacityChanger(this, hairOpacitySlider);
		OpacityHelper.bodyOpacityChanger(this, bodyOpacitySlider);
		OpacityHelper.limbsOpacityChanger(this, limbsOpacitySlider);
		OpacityHelper.shoesOpacityChanger(this, shoesOpacitySlider);
		OpacityHelper.lipsOpacityChanger(this, lipsOpacitySlider);
		OpacityHelper.eyeOpacityChanger(this, eyeOpacitySlider);
		OpacityHelper.browOpacityChanger(this, browOpacitySlider);
		OpacityHelper.noseOpacityChanger(this, noseOpacitySlider);
		
		ExitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) ExitButton.getScene().getWindow();
				stage.close();
				System.exit(0);
			}
		});
	}

	@FXML
	private void handleBG1() {
		String bg1 = getClass().getClassLoader().getResource("Images/bg1.jpg").toExternalForm();
		StickmanStage.sStickmanHBox.setStyle("-fx-background-image: url('" + bg1 + "'); "
				+ "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
	}

	@FXML
	private void handleBG2() {
		String bg2 = getClass().getClassLoader().getResource("Images/bg2.jpg").toExternalForm();
		StickmanStage.sStickmanHBox.setStyle("-fx-background-image: url('" + bg2 + "'); "
				+ "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
	}

	@FXML
	private void handleBG3() {
		String bg3 = getClass().getClassLoader().getResource("Images/bg3.jpg").toExternalForm();
		StickmanStage.sStickmanHBox.setStyle("-fx-background-image: url('" + bg3 + "'); "
				+ "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
	}

	@FXML
	private void handleBG4() {
		String bg4 = getClass().getClassLoader().getResource("Images/bg4.jpg").toExternalForm();
		StickmanStage.sStickmanHBox.setStyle("-fx-background-image: url('" + bg4 + "'); "
				+ "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
	}

	@FXML
	private void handleBG5() {
		String bg5 = getClass().getClassLoader().getResource("Images/bg5.jpg").toExternalForm();
		StickmanStage.sStickmanHBox.setStyle("-fx-background-image: url('" + bg5 + "'); "
				+ "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
	}

	@FXML
	private void handleBG6() {
		String bgDefault = getClass().getClassLoader().getResource("Images/bgDefault.png").toExternalForm();
		StickmanStage.sStickmanHBox.setStyle("-fx-background-image: url('" + bgDefault + "'); "
				+ "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
	}

	@FXML
	public void handleStopCamera() {
		if (isCameraStarted) {
			StickmanStage.sSubscene.setCamera(null);
			isCameraStarted = false;
		}
	}

	@FXML
	public void handleStartCamera() {
		if (!isCameraStarted) {
			StickmanStage.sSubscene.setCamera(StickmanStage.sCamera);
			isCameraStarted = true;
		}
	}

	@FXML
	public void handleResetCamera() {

		StickmanStage.sCamera.setTranslateX(StickmanStage.recordCameraXPosition);
		StickmanStage.sCamera.setTranslateY(StickmanStage.recordCameraYPosition);
		StickmanStage.sCamera.setTranslateZ(StickmanStage.recordCameraZPosition);

		cameraXSlider.setValue(0);
		cameraYSlider.setValue(0);
		cameraZSlider.setValue(0);
		cameraXTranslationField.setText("0.0");
		cameraYTranslationField.setText("0.0");
		cameraZTranslationField.setText("0.0");
		StickmanStage.sCamera.setNearClip(0.8);
		StickmanStage.sCamera.setFarClip(3000);
		StickmanStage.sCamera.setFieldOfView(30);
		nearClipField.setText("0.8");
		farClipField.setText("3000");
		fieldOfViewField.setText("30");
		StickmanStage.sCamera.getTransforms().clear();

	}
	
	@FXML 
	public void handleCameraXTranslation(MouseEvent event)
	{
		if(event.getSource().equals(cameraXPlusTranslationButton))
		{
			double currentValue = Double.parseDouble(cameraXTranslationField.getText());
			currentValue+=50;
			StickmanStage.sCamera.setTranslateX(StickmanStage.sCamera.getTranslateX() + 50);
			cameraXTranslationField.setText(Double.toString(currentValue));
		}
		else if(event.getSource().equals(cameraXMinusTranslationButton))
		{
			double currentValue = Double.parseDouble(cameraXTranslationField.getText());
			currentValue-=50;
			StickmanStage.sCamera.setTranslateX(StickmanStage.sCamera.getTranslateX() - 50);
			cameraXTranslationField.setText(Double.toString(currentValue));
		}
	}
	
	@FXML 
	public void handleCameraYTranslation(MouseEvent event)
	{
		if(event.getSource().equals(cameraYPlusTranslationButton))
		{
			double currentValue = Double.parseDouble(cameraYTranslationField.getText());
			currentValue+=50;
			StickmanStage.sCamera.setTranslateY(StickmanStage.sCamera.getTranslateY() + 50);
			cameraYTranslationField.setText(Double.toString(currentValue));
		}
		else if(event.getSource().equals(cameraYMinusTranslationButton))
		{
			double currentValue = Double.parseDouble(cameraYTranslationField.getText());
			currentValue-=50;
			StickmanStage.sCamera.setTranslateY(StickmanStage.sCamera.getTranslateY() - 50);
			cameraYTranslationField.setText(Double.toString(currentValue));
		}
	}
	
	@FXML 
	public void handleCameraZTranslation(MouseEvent event)
	{
		if(event.getSource().equals(cameraZPlusTranslationButton))
		{
			double currentValue = Double.parseDouble(cameraZTranslationField.getText());
			currentValue+=10;
			StickmanStage.sCamera.setTranslateZ(StickmanStage.sCamera.getTranslateZ() + 10);
			cameraZTranslationField.setText(Double.toString(currentValue));
		}
		else if(event.getSource().equals(cameraZMinusTranslationButton))
		{
			double currentValue = Double.parseDouble(cameraZTranslationField.getText());
			currentValue-=10;
			StickmanStage.sCamera.setTranslateZ(StickmanStage.sCamera.getTranslateZ() - 10);
			cameraZTranslationField.setText(Double.toString(currentValue));
		}
	}
	
	@FXML
	public void handleNearClip(MouseEvent event)
	{
		if(event.getSource().equals(nearClipPlusButton))
		{
			double currentValue = Double.parseDouble(nearClipField.getText());
			if(currentValue>=1.0)
				currentValue = 1.0;
			else
			{
				currentValue += 0.1;
				currentValue = Math.round(currentValue * 100.0) / 100.0;
			}
			StickmanStage.sCamera.setNearClip(currentValue);
			nearClipField.setText(Double.toString(currentValue));
		}
		else if(event.getSource().equals(nearClipMinusButton))
		{
			double currentValue = Double.parseDouble(nearClipField.getText());
			if(currentValue<=0.0)
				currentValue = 0.0;
			else
			{
				currentValue -= 0.1;
				currentValue = Math.round(currentValue * 100.0) / 100.0;
			}
			StickmanStage.sCamera.setNearClip(currentValue);
			nearClipField.setText(Double.toString(currentValue));
		}
	}
	
	@FXML
	public void handleFarClip(MouseEvent event)
	{
		if(event.getSource().equals(farClipPlusButton))
		{
			double currentValue = Double.parseDouble(farClipField.getText());
			currentValue += 50;
			
			StickmanStage.sCamera.setFarClip(currentValue);
			farClipField.setText(Double.toString(currentValue));
		}
		else if(event.getSource().equals(farClipMinusButton))
		{
			double currentValue = Double.parseDouble(farClipField.getText());
			currentValue -= 50;
			
			StickmanStage.sCamera.setFarClip(currentValue);
			farClipField.setText(Double.toString(currentValue));
		}
	}
	
	@FXML
	public void handleFieldOfView(MouseEvent event)
	{
		if(event.getSource().equals(fieldOfViewPlusButton))
		{
			double currentValue = Double.parseDouble(fieldOfViewField.getText());
			currentValue += 1;
			
			StickmanStage.sCamera.setFieldOfView(currentValue);
			fieldOfViewField.setText(Double.toString(currentValue));
		}
		else if(event.getSource().equals(fieldOfViewMinusButton))
		{
			double currentValue = Double.parseDouble(fieldOfViewField.getText());
			currentValue -= 1;
			
			StickmanStage.sCamera.setFieldOfView(currentValue);
			fieldOfViewField.setText(Double.toString(currentValue));
		}
	}

	@FXML
	public void handleHeadColor() 
	{
		ColorHelper.headColorChanger(currentStickman, headColorPicker);
	}

	@FXML
	public void handleHairColor() {
		ColorHelper.hairColorChanger(currentStickman, hairColorPicker);
	}

	@FXML
	public void handleBodyColor() {
		ColorHelper.bodyColorChanger(currentStickman, bodyColorPicker);
	}

	@FXML
	public void handleLimbsColor() {
		ColorHelper.limbsColorChanger(currentStickman, limbsColorPicker);
	}

	@FXML
	public void handleShoesColor() {
		ColorHelper.shoesColorChanger(currentStickman, shoesColorPicker);
	}

	@FXML
	public void handleLipsColor() {
		ColorHelper.lipsColorChanger(currentStickman, lipsColorPicker);
	}

	@FXML
	public void handleEyeColor() {
		ColorHelper.eyeColorChanger(currentStickman, eyeColorPicker);
	}

	@FXML
	public void handleBrowColor() {
		ColorHelper.browColorChanger(currentStickman, browColorPicker);
	}
	
	@FXML
	public void handleNoseColor() {
		ColorHelper.noseColorChanger(currentStickman, noseColorPicker);
	}

	@FXML
	public void handleHeadColorButtons(MouseEvent ev) {
		ColorHelper.handleHeadColorButtons(currentStickman, ev, headColorBrighter, headColorDarker, headColorReset);
	}

	@FXML
	public void handleHairColorButtons(MouseEvent ev) {
		ColorHelper.handleHairColorButtons(currentStickman, ev, hairColorBrighter, hairColorDarker, hairColorReset);
	}

	@FXML
	public void handleBodyColorButtons(MouseEvent ev) {
		ColorHelper.handleBodyColorButtons(currentStickman, ev, bodyColorBrighter, bodyColorDarker, bodyColorReset);
	}

	@FXML
	public void handleLimbsColorButtons(MouseEvent ev) {
		ColorHelper.handlelimbsColorButtons(currentStickman, ev, limbsColorBrighter, limbsColorDarker, limbsColorReset);
	}

	@FXML
	public void handleShoesColorButtons(MouseEvent ev) {
		ColorHelper.handleShoesColorButtons(currentStickman, ev, shoesColorBrighter, shoesColorDarker, shoesColorReset);
	}

	@FXML
	public void handleLipsColorButtons(MouseEvent ev) {
		ColorHelper.handleLipsColorButtons(currentStickman, ev, lipsColorBrighter, lipsColorDarker, lipsColorReset);
	}

	@FXML
	public void handleEyeColorButtons(MouseEvent ev) {
		ColorHelper.handleEyeColorButtons(currentStickman, ev, eyeColorBrighter, eyeColorDarker, eyeColorReset);
	}

	@FXML
	public void handleBrowColorButtons(MouseEvent ev) {
		ColorHelper.handleBrowColorButtons(currentStickman, ev, browColorBrighter, browColorDarker, browColorReset);
	}
	
	@FXML
	public void handleNoseColorButtons(MouseEvent ev) {
		ColorHelper.handleNoseColorButtons(currentStickman, ev, noseColorBrighter, noseColorDarker, noseColorReset);
	}

	public void getStickmanStageFX(StickmanStage Stickmanstage) {
		this.mStickmanstage = Stickmanstage;
		fillComboForStickman();

	}

	private void fillEmotionScrollPane() {
		ArrayList<String> getClassesNames;
		Packageparser parser = new Packageparser(packEmotionExpression);
		getClassesNames = parser.getClassNameList();
		ObservableList<String> classNames = FXCollections.observableArrayList();
		classNames.addAll(getClassesNames.stream().collect(Collectors.toList()));

		createAndHandleRadioButtons(getClassesNames, emotionsScrollPane);
	}

	private void fillGestureScrollPane() {
		ArrayList<String> getClassesNames;
		Packageparser parser = new Packageparser(packGesture);
		getClassesNames = parser.getClassNameList();
		ObservableList<String> classNames = FXCollections.observableArrayList();
		classNames.addAll(getClassesNames.stream().collect(Collectors.toList()));

		createAndHandleRadioButtons(getClassesNames, gestureScrollPane);
	}

	private void fillHeadScrollPane() {
		ArrayList<String> getClassesNames;
		Packageparser parser = new Packageparser(packHead);
		getClassesNames = parser.getClassNameList();
		ObservableList<String> classNames = FXCollections.observableArrayList();
		classNames.addAll(getClassesNames.stream().collect(Collectors.toList()));

		createAndHandleRadioButtons(getClassesNames, headScrollPane);
	}

	private void fillEnvironmentScrollPane() {
		ArrayList<String> getClassesNames;
		Packageparser parser = new Packageparser(packEnvironment);
		getClassesNames = parser.getClassNameList();
		ObservableList<String> classNames = FXCollections.observableArrayList();
		classNames.addAll(getClassesNames.stream().collect(Collectors.toList()));

		createAndHandleRadioButtons(getClassesNames, environmentScrollPane);
	}

	private void fillPostureScrollPane() {
		ArrayList<String> getClassesNames;
		Packageparser parser = new Packageparser(packPosture);
		getClassesNames = parser.getClassNameList();
		ObservableList<String> classNames = FXCollections.observableArrayList();
		classNames.addAll(getClassesNames.stream().collect(Collectors.toList()));

		createAndHandleRadioButtons(getClassesNames, postureScrollPane);
	}

	private void createAndHandleRadioButtons(ArrayList<String> getClassesNames, ScrollPane container) {
		GridPane gridPane = new GridPane();
		container.setContent(gridPane);
		ToggleGroup toggleGroup = new ToggleGroup();

		int startIndex = 0;
		int endIndex = 0;

		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		for (int i = 0; i < getClassesNames.size(); i++) {
			RadioButton button = new RadioButton(getClassesNames.get(i));
			button.setToggleGroup(toggleGroup);
			button.getStylesheets().add(this.getClass().getResource("RadioButtonCSS.css").toExternalForm());
			button.setFont(Font.font("Arial", 15));

			button.setOnAction((event) -> {
				currentRadioButton = (RadioButton) event.getSource();
				currentStickman.doAnimation(button.getText(), 500, true);
			});
			if (i % 3 == 2) {
				gridPane.add(button, startIndex, endIndex);
				endIndex++;
				startIndex = 0;
			} else {
				gridPane.add(button, startIndex, endIndex);
				startIndex++;
			}
		}
	}

	private void fillComboForStickman() {
		ObservableList<String> stickmanNames = FXCollections.observableArrayList();
		stickmanNames.addAll(mStickmanstage.mStickmanComboList.stream().collect(Collectors.toList()));
		StickmanComboBox.getItems().addAll(stickmanNames);
		StickmanComboBox.setValue(stickmanNames.get(0));
		currentStickman = StickmanStage.getStickmanFX(stickmanNames.get(0));
	}

	@FXML
	private void handleWithPerlinNoise() {

		for (String key : mStickmanstage.mStickmanComboList) {
			StickmanStage.getStickmanFX(key).doAnimation("StartIdle", 1000, true);
		}

	}

	@FXML
	private void handleWithoutPerlinNoise() {

		for (String key : mStickmanstage.mStickmanComboList) {
			StickmanStage.getStickmanFX(key).doAnimation("StopIdle", 1000, true);
		}
	}

}
