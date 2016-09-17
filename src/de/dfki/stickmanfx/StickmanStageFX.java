package de.dfki.stickmanfx;
import de.dfki.common.GeneralStageRoot;
import de.dfki.common.StageStickman;
import de.dfki.common.StickmansOnStage;
import de.dfki.stickmanfx.stagecontroller.StageStickmanControllerFX;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
/**
 *
 * @author Robbie. Refactored by: acepero13
 *
 */
public class StickmanStageFX extends Application implements StageStickman{
    static private StickmanStageFX sInstance;
    Stage configStage;
    private StickmansOnStage stickamnsOnStage;
    private  float sScale = 1.0f;
    public static   boolean isRunning = false;
    private HashMap<String, Stage> stickmanFXStages = new HashMap<>();
    private LinkedList<String> stickmanNames = new LinkedList<>();
    private GeneralStageRoot generalConfigStageRoot;

    public StickmanStageFX() { //This cannot be private because of ApplicationFX
        Platform.setImplicitExit(false);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new StickmanStageLogFormatter());
        sInstance = this;
    }

    public float getFullScreenScale(){
        return getHeight() / (float) StickmanFX.mDefaultSize.height * sScale * 0.8f;
    }

    public Dimension getFullScreenDimension(){
        return new Dimension(new Float(getHeight() * 3 / 5 * sScale).intValue(), new Float(getHeight() * sScale * 0.9f ).intValue());
    }

    private float getHeight(){
        Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        return size.height;
    }

    public static StickmanStageFX getInstance(){
        return sInstance;
    }

    public void start(Stage stage) throws Exception {
        configStage = stage;
        HBox root = getConfigRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("StickmanCSS.css").toExternalForm());
        stage.setTitle("StickmanFX");
        stage.setScene(scene);
        stickmanFXStages.put(StageStickmanControllerFX.CONFIG_STAGE, stage);
        isRunning = true;
    }

    private HBox getConfigRoot() throws java.io.IOException {
        generalConfigStageRoot = new GeneralStageRoot();
        return generalConfigStageRoot.getConfigRoot();
    }

    private HBox getStageRoot() throws java.io.IOException {
        GeneralStageRoot generalStageRoot = new GeneralStageRoot();
        return generalStageRoot.getStageRoot();
    }

    public String createNewStage() throws IOException {
        String uuid = UUID.randomUUID().toString();
        try {
            createStage(uuid);
            waitForCreatingStage(uuid);
        } catch (IOException e) {
            throw e;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uuid;
    }

    public void waitForCreatingStage(String uuid) throws InterruptedException {
        while (!stickmanFXStages.containsKey(uuid)){
            Thread.sleep(200);
        }
    }

    public void createStage(String uuid) throws IOException {
        final HBox root = getStageRoot();
        Platform.runLater(()->{
            Scene stageScene =  new Scene(root);
            Stage stage = new Stage();
            stage.setScene(stageScene);
            stickmanFXStages.put(uuid, stage);
        });
    }

    public void lauchStickman() {
        launch();
    }

    public void setStickamnsOnStage(StickmansOnStage stickamnsOnStage) {
        this.stickamnsOnStage = stickamnsOnStage;
        generalConfigStageRoot.setStickmansOnStage(stickamnsOnStage);
    }

    public  void addStickmanToStage(String stageIdentifier) throws Exception {
        HBox sStickmanPane;
        sStickmanPane = getStickmanPane(stageIdentifier);
        sStickmanPane.getChildren().clear();
        for(String key : stickamnsOnStage.getStickmanNames()) {
            sStickmanPane.getChildren().add(stickamnsOnStage.getStickmanByKey(key));
            addStickmanName(key);
        }
    }

    private HBox getStickmanPane(String stageIdentifier) throws Exception {
        HBox sStickmanPane;
        if(stickmanFXStages.containsKey(stageIdentifier)){
            sStickmanPane = (HBox) ((ScrollPane)stickmanFXStages.get(stageIdentifier).getScene().getRoot().lookup("#stickmanScrollPane")).getContent();
        }
        else{
            throw new Exception("Stage Not found");
        }
        return sStickmanPane;
    }

    private void addStickmanName(String key) {
        if(!getStickmanNames().contains(key)) {
            getStickmanNames().add(key);
            generalConfigStageRoot.getmStickmanStageController().fillComboForStickman();
        }
    }

    public void showStage(String stageIdentifier) {
        if(stickmanFXStages.containsKey(stageIdentifier)){
            Platform.runLater(()->stickmanFXStages.get(stageIdentifier).show());
        }
    }

    public LinkedList<String> getStickmanNames() {
        return stickmanNames;
    }

    private static class StickmanStageLogFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            return ((new StringBuffer()).append(record.getLevel()).append(": ").append(record.getMessage()).append("\n")).toString();
        }
    }
}