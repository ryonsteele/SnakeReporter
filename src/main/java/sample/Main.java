package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.models.CodeDTO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {

    //This is our PrimaryStage (It contains everything)
    private Stage primaryStage;

    //This is the BorderPane of RootLayout
    private BorderPane rootLayout;

    public static HashMap<String,CodeDTO> map=new HashMap<String,CodeDTO>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        //1) Declare a primary stage (Everything will be on this stage)
        this.primaryStage = primaryStage;

        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("Snake Reporter");

        dataLoader();

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the EmployeeOperations View
        showTableView();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../rootStage.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.

            //Third, show the primary stage
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Shows the employee operations view inside the root layout.
    public void showTableView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../imageTableStage.fxml"));
            AnchorPane tableView = (AnchorPane) loader.load();

            rootLayout.setCenter(tableView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataLoader(){

        map.put("CL",new CodeDTO("CL", "Longitudinal Crack", " A crack runs lengthwise along the axis of the pipe (parallel to the centerline of the pipeline"));
        map.put("CC",new CodeDTO("CC", "Circumferential Crack", " A crack runs in a circular pattern perpendicular to the axis of the pipe (parallel to the joints)."));
        map.put("CM",new CodeDTO("CM", "Multiple Cracks", " There is a combination of both longitudinal and circumferential cracks that intersect."));
        map.put("CMC",new CodeDTO("CMC", "Multiple Cracks/Crazing", " There is a combination of longitudinal and circumferential fine surface cracks�. A craze pattern can develop immediately after the pipe is removed from the kiln or years later. A craze pattern is different from a crack in that it usually cannot be felt on the surface and it may continue to support a load. Sometimes referred to as a shallow map or pattern cracking crazing does not always affect the structural integrity�or the durability of wear resistance of the pipe material."));
        map.put("CS",new CodeDTO("CS", "Spiral Crack", " There is an individual crack that changes position as it travels along the pipe. It either; A) starts down the pipe longitudinally and then turns in a circumferential direction or B) starts longitudinally at a joint--and then returns to the original joint. Spiral cracks typically do not travel from one pipe section to the next across a joint."));
        map.put("CH",new CodeDTO("CH", "Hinge Cracks", " There are more than one longitudinal crack occurring at the same footage at the 1200 300 600 and/or 900 clock positions. Hinges are almost always a result of excessive vertical loading on the pipe that causes the pipe to be in stress circumferentially�possibly the result of improper installation."));
        map.put("FL",new CodeDTO("FL", "Longitudinal Fracture", " A fracture runs lengthwise along the axis of the pipe (parallel to the centerline of the pipeline)."));
        map.put("FC",new CodeDTO("FC", "Circumferential Fracture", " A fracture runs in a circular pattern perpendicular to the axis of the pipe (parallel to the joints)."));
        map.put("FM",new CodeDTO("FM", "Multiple Fractures", " There is a combination of both longitudinal and circumferential fractures that intersect."));
        map.put("FS",new CodeDTO("FS", "Spiral Fracture", " There is an individual fracture that changes position as it travels along the pipe. It either; A) starts down the pipe longitudinally and then turns in a circumferential direction or B) starts longitudinally at a joint--and then returns to the original joint. A spiral fracture may travel down the length of the pipe before turning--beginning and ending at the same joint."));
        map.put("FH",new CodeDTO("FH", "Hinge Fractures", " There are more than one longitudinal fracture�or a combination of longitudinal crack(s) and longitudinal fracture(s) are occurring at the same footage at the 1200 300 600 and/or 900 clock positions. Hinges are almost always a result of excessive vertical loading on the pipe that causes the pipe to be in stress circumferentially--possibly the result of improper installation."));
        map.put("B",new CodeDTO("B", "Broken", " Pieces/sections of the pipe material have become noticeably broken and displaced--and have moved/shifted from their original position. Soil surrounding the pipe is not visible."));
        map.put("BSV",new CodeDTO("BSV", "Broken Soil Visible", " Soil surrounding the pipe is visible beyond the break in the pipe. This means that although the outside of the pipe is visible. The soil surrounding the pipe is still in place and does not appear to be eroded."));
        map.put("BVV",new CodeDTO("BVV", "Broken Void Visible", " A void or cavity in the soil is visible beyond the break in the pipe. This means that the outside of the pipe is visible and a void or cavity can be seen outside the pipe."));
        map.put("H",new CodeDTO("H", "Hole", " A section of the pipe material is missing."));
        map.put("HSV",new CodeDTO("HSV", "Hole Soil Visible", " Soil surrounding the pipe is visible beyond the hole in the pipe. Although the outside of the pipe is visible the soil surrounding the pipe is still in place and does not appear to be eroded."));
        map.put("HVV",new CodeDTO("HVV", "Hole Void Visible", " A void or cavity is visible beyond the hole in the pipe. The outside of the pipe is visible and a void or cavity in the soil can be seen outside the pipe."));
        map.put("DR",new CodeDTO("DR", "Deformed Rigid", " Examples of pipe materials include Asbestos Cement (AC) Cast Iron (CAS) Concrete (CP RCP) and Vitrified Clay Tile (VCP)."));
        map.put("DF",new CodeDTO("DF", "Deformed Flexible", " Examples of pipe materials include Plastic (PVC) Corrugated Metal (CMP), Reinforced Plastic Pipe/Truss Pipe (RMP) Orangeburg (OB)�a well as pipe lining methods (CIPP)."));
        map.put("DFBR",new CodeDTO("DFBR", "Deformed Flexible Bulging Round", " There are one or more rounded projections into the pipe."));
        map.put("DFBI",new CodeDTO("DFBI", "Deformed Flexible Bulging Inverse Curvature", " A defect characterized by a sharp-crested inward bulge that resembles a heart-shape point and/or a shark fin�typically a continuous defect."));
        map.put("DFC",new CodeDTO("DFC", "Deformed Flexible Creasing", " A sharp outward folding of the pipe wall in the in the longitudinal direction�usually a continuous defect."));
        map.put("DFE",new CodeDTO("DFE", "Deformed Flexible Elliptical", " An alteration of the original pipe shape in which the pipe is compressed into the shape of an oval."));
        map.put("DT",new CodeDTO("DT", "Deformed Brick", " Pipe materials include Brick (BR) and Clay Tile (CT)."));
        map.put("DTBR",new CodeDTO("DTBR", "Deformed Brick Bulging Round", " One or more rounded projections into the pipe. This condition can be local or continuous�measured as an estimated percentage of the cross-sectional area lost."));
        map.put("DTBI",new CodeDTO("DTBI", "Deformed Brick Bulging Inverse Curvature", " A defect characterized by a sharp-crested inward bulge that resembles a heart-shape point and/or a shark fin�typically a continuous defect."));
        map.put("JO",new CodeDTO("JO", "Joint Offset Displaced", " A pipe joint in which the spigot end of one pipe is not in-line concentric or flush with the socket or bell or adjoining edge of the adjacent pipe."));
        map.put("JOS",new CodeDTO("JOS", "Joint Offset Small", " Only available for dam and levee pipes; noticeable offset is less than (<) 1.5 of the pipe wall thickness."));
        map.put("JOM",new CodeDTO("JOM", "Joint Offset Medium", " Offset is greater than or equal to (>) 1 (one) pipe wall thickness�but less than (<) 1.5 (one and one half) the pipe wall thickness."));
        map.put("JOL",new CodeDTO("JOL", "Joint Offset Large", " Offset is greater than or equal to (>) 1.5 (one and one half) the pipe wall thickness."));
        map.put("JOSD",new CodeDTO("JOSD", "Joint Offset Small Defective", " Only available for dam and levee pipes; noticeable offset is less than (<) 1.5 of the pipe wall thickness."));
        map.put("JOMD",new CodeDTO("JOMD", "Joint Offset Medium Defective", " Offset is greater than or equal to (>) 1 (one) pipe wall thickness�but less than (<) 1.5 (one and one half) the pipe wall thickness."));
        map.put("JOLD",new CodeDTO("JOLD", "Joint Offset Large Defective", " Offset is greater than or equal to (>) 1.5 (one and one half) the pipe wall thickness."));
        map.put("JSS",new CodeDTO("JSS", "Joint Separation Small", " Only available for dam and levee pipes. Noticeable separation between pipe segments but gap is not visible."));
        map.put("JSM",new CodeDTO("JSM", "Joint Separation Medium", " Separation is up to 1 (one) pipe wall thickness."));
        map.put("JSL",new CodeDTO("JSL", "Joint Separation Large", " Separation is greater than (>) 1 (one) pipe wall thickness."));
        map.put("JAS",new CodeDTO("JAS", "Joint Angular Small", " Only available for dam and levee pipes. The change in alignment is less than or equal to (<) 5 (five) degrees."));
        map.put("JAM",new CodeDTO("JAM", "Joint Angular Medium", " The change in alignment is greater than (>) 5 (five) degrees."));
        map.put("JAL",new CodeDTO("JAL", "Joint Angular Large", " The change in alignment is greater than (>) 10 (ten) degrees."));
        map.put("JMM",new CodeDTO("JMM", "Misaligned Joint", " There are one or more joints in the pipe that have minor-to-moderate offsets but do not appear to be completely separated or leaking.  In the past angling or cocking� sections of older clay tile�and sometimes concrete pipe�at the joints was not unusual during installation to adjust the angle/direction of the line. Resulting in misaligned seams between sections of pipe."));
        map.put("SRI",new CodeDTO("SRI", "Surface Damage Roughness Increased", " Slight surface damage in which the surface of the pipe or brickwork is slightly worn or abraded or deteriorated."));
        map.put("SAV",new CodeDTO("SAV", "Surface Damage Aggregate Visible", " Moderate surface damage in which the pipe aggregate is visible due to the cement in the concrete pipe material being worn away�exposing the aggregate."));
        map.put("SAP",new CodeDTO("SAP", "Surface Damage Aggregate Projecting", " Surface damage in which some of the aggregate in the concrete pipe is visible and projecting above the surface of the remaining concrete matrix."));
        map.put("SAM",new CodeDTO("SAM", "Surface Damage Aggregate Missing", " The appearance of pitting of the pipe wall is an indication of missing aggregate."));
        map.put("SRV",new CodeDTO("SRV", "Surface Damage Reinforcement Visible", " Eroded/missing concrete has exposed the reinforcement (normally steel rebar) within the pipe wall�usually associated with Hydrogen Sulfide."));
        map.put("SRP",new CodeDTO("SRP", "Surface Damage Reinforcement Projecting", " Serious surface damage in which reinforced concrete pipe erosion has resulted in reinforcement (normally steel rebar) projecting out from the pipe wall�usually associated with Hydrogen Sulfide."));
        map.put("SRC",new CodeDTO("SRC", "Surface Damage Reinforcement Corroded", " Severe surface damage in reinforced concrete pipe in which the reinforcement (normally steel rebar) is clearly visible and corroded�with sections both missing and projecting to the surface. The rate of deterioration usually associated with Hydrogen Sulfide is greater with concrete than the reinforcement�typically resulting in an unequal rate of corrosion."));
        map.put("SMW",new CodeDTO("SMW", "Surface Damage Missing Wall", " Severe surface damage in which 1 (one) or more portions of missing pipe material have completely eroded away over time. Missing sections will be adjacent to other visible and in place pipe sections that also exhibit severe surface damage."));
        map.put("SSS",new CodeDTO("SSS", "Surface Damage Surface Spalling", " Spalling is a spontaneous separation/fragmentation of the pipe surface due to internal stresses. Surface spalling may also be the result of defective or damaged or improperly stored pipe material. "));
        map.put("SSC",new CodeDTO("SSC", "Surface Damage Spalling of Coating", " Surface damage inside pipes that have been coated. The inside surface of the coating is damaged or abraded or flaked or splintered off. "));
        map.put("SCP",new CodeDTO("SCP", "Surface Damage Corrosion", " Only applicable for metal pipe. Such as Corrugated Metal (CMP) or Cast Iron (CAS) or Steel (SP) and Ductile Iron (DIP). Corrosion has caused missing pipe wall. "));
        map.put("SZ",new CodeDTO("SZ", "Surface Damage Other", " Surface damage defect in brick or concrete or clay and/or flexible pipe not suitably classified by any of the existing codes. "));
        map.put("LFAC",new CodeDTO("LFAC", "Lining Feature Abandoned Connection", " The pipe liner is installed over a tie-in/connection�creating a visible dimple. "));
        map.put("LFAS",new CodeDTO("LFAS", "Lining Feature Annular Space", " The pipe liner does not fit closely against the wall of the host pipe�resulting in a significant/visible gap between the liner and the pipe. This defect is typically observed at the interface between the access point and the pipe at either end of a patch repair or at a tap connection."));
        map.put("LFB",new CodeDTO("LFB", "Lining Feature Blistered", " The pipe liner interior coating contains pockets or sheets etc. (e.g. air or resin or water or solvent) that causes a blistered effect on the inside surface of the liner. This defect can also be produced when the internal heat of a CIPP pipe liner affects the stability of the internal liner coating material during the initial curing process. "));
        map.put("LFCS",new CodeDTO("LFCS", "Lining Feature Service (Branch Line) Cut Shifted", " The pipe liner has moved and/or the liner has shrunk relative to the service pipe (branch line) connection opening. As a result the lining may obstruct the service pipe connection as well as exposing the original pipe. This condition usually occurs when the liner shifts after the service pipe connections are cut. "));
        map.put("LFD",new CodeDTO("LFD", "Lining Feature Detached", " The pipe liner is detached from the host pipe."));
        map.put("LFDE",new CodeDTO("LFDE", "Lining Feature Defective End", " The end of the pipe liner is defective. The defective end may be ragged or warped. The liner may also have shrunk and the end of the liner has withdrawn back inside the pipe. "));
        map.put("LFDL",new CodeDTO("LFDL", "Lining Feature Delamination", " Layers of the pipe liner material have become separated during installation and are not homogeneous."));
        map.put("LFOC",new CodeDTO("LFOC", "Lining Feature Overcut Service (Branch Line)", " Too much of the pipe liner material was cut from the service connection (branch line) when it was reinstated leaving part of the host pipe exposed without a lining. "));
        map.put("LFRS",new CodeDTO("LFRS", "Lining Feature Resin Slug", " Excessive resin has flowed into and cured at a service (branch line) connection during the CIPP pipe liner installation. The cured (hardened) mass of resin is partially or fully blocking the flow from the connection. "));
        map.put("LFUC",new CodeDTO("LFUC", "Lining Feature Undercut Service (Branch Line)", " An insufficient amount of pipe liner material is cut away from a reinstated service (branch line) connection. An undercut service (branch line) connection may restrict flow from the lateral and result in debris snagging on the liner potentially causing a blockage. "));
        map.put("LFW",new CodeDTO("LFW", "Lining Feature Wrinkled ", " Defect in which the cured CIPP pipe liner has wrinkled. Wrinkles are most common in bends in which excess material at the inside radius of a turn bunches� up; longitudinal and circumferential wrinkles can occur if the host pipe is smaller than the diameter of the liner being installed. Wrinkles may cause disruption to the flow/operation of the pipe particularly when located at the invert. "));
        map.put("LFZ",new CodeDTO("LFZ", "Lining Feature Other", " A pipe lining feature/defect which is not accurately represented by the previous Lining Feature Codes."));
        map.put("WFC",new CodeDTO("WFC", "Weld Failure Circumferential", " Weld Failure occurs around the circumference of the pipe."));
        map.put("WFL",new CodeDTO("WFL", "Weld Failure Longitudinal", " Weld failure is mainly parallel to the axis of the pipe. "));
        map.put("WFM",new CodeDTO("WFM", "Weld Failure Multiple", " A combination of both longitudinal and circumferential failures (typically cannot be easily identified and coded as either longitudinal or circumferential�or are too numerous to code separately)."));
        map.put("WFS",new CodeDTO("WFS", "Weld Failure Spiral", " Weld failure occurs in large diameter plastic spirally wound welded pipes."));
        map.put("WFZ",new CodeDTO("WFZ", "Weld Failure Other", "  Existing Weld Failure (WF) Codes no not accurately represent the weld failure defect. A description of the defect should be entered in the comments section."));
        map.put("RPL",new CodeDTO("RPL", "Point Repair Liner", " A repair was made by placing a short CIPP (pipe liner) over the defective portion of the pipe."));
        map.put("RPP",new CodeDTO("RPP", "Point Repair Patch", " A patch was installed to repair a hole or other defect in the pipe�leaving the original pipe in place. "));
        map.put("RPR",new CodeDTO("RPR", "Point Repair Replacement", " A section of the original pipe was removed and replaced with new pipe. A point repair is often performed with different pipe material from the originally installed pipe."));
        map.put("RPZ",new CodeDTO("RPZ", "Point Repair Other", "  A point repair which is not covered by any of the existing Point Repair descriptors (see remarks/description)."));
        map.put("DB",new CodeDTO("DB", "Displaced Brick", " Area(s) of the sewer line in which bricks have shifted position from their original position. "));
        map.put("MB",new CodeDTO("MB", "Missing Brick", " Area(s) of the sewer line in which one or more bricks are missing. More than one layer may be affected."));
        map.put("DJ",new CodeDTO("DJ", "Dropped Invert", " Area(s) of the sewer line in which the channel of the brickwork has dropped� relative to the sewer line wall�with a pronounced gap of more than one inch between the invert (bottom of line) and wall. The channel may be dropped on one or both sides."));
        map.put("MMS",new CodeDTO("MMS", "Missing Mortar Small", " Mortar loss less than (<) 0.5 inch."));
        map.put("MMM",new CodeDTO("MMM", "Missing Mortar Medium", " Mortar loss equal to or greater than (>) 0.5 inch and less than (<) 2 inches."));
        map.put("MML",new CodeDTO("MML", "Missing Mortar Large", " Mortar loss greater than (>) 2 inches."));
        map.put("DA",new CodeDTO("DA", "Deposits Attached", " Attached deposits are foreign martials that cling to the inner wall of the pipe and often continue to accumulate�reducing the cross-sectional area of the pipe. "));
        map.put("DAGS",new CodeDTO("DAGS", "Deposit Attached Grease", " Grease deposits attached to the sides of the pipe above the flow line. Frequently surcharged (completely submerged and under pressure) pipe may accumulate grease at the pipe crown. "));
        map.put("DAR",new CodeDTO("DAR", "Deposits Attached Ragging", " Attached deposits made up of paper debris or bathroom trash or personal hygiene products or other refuse. Rags may snag on defects such as roots or broken pipe or misaligned pipe joints. "));
        map.put("DAZ",new CodeDTO("DAZ", "Deposits Attached Other", " Attached deposits which are not correctly classified by any of the existing Deposits Attached (DA) descriptors."));
        map.put("DS",new CodeDTO("DS", "Deposits Settled", " Settled/deposited materials located in the invert (bottom) of the pipe/sewer--or at the bottom of the vertical stack/cleanout/access structure. Most evident in sections of pipe with a flatter grade. Settled deposits are often distributed throughout the length of the pipe and are recorded by the most prevalent size of the particles or hardness. Due to the high difficulty of specifically identifying various deposit materials�no attempt is normally made to distinguish material types.  "));
        map.put("DSC",new CodeDTO("DSC", "Deposits Settled Hard/Compacted", " Settled deposits that have hardened in the pipe such as concrete or grout. "));
        map.put("DSZ",new CodeDTO("DSZ", "Deposits Settled Other", " A settled deposit which is not correctly classified by any of the existing Deposits Settled (DS) descriptors. "));
        map.put("DN",new CodeDTO("DN", "Deposits Ingress", " Soil/backfill material has washed into the pipe by the infiltration of groundwater. These deposits will usually be concentrated around faulty joints and/or other defects. Ingress deposits indicate a potentially serious condition in which the surrounding soil/bedding intruding into the pipe may likely result in the development of voids to the exterior of the pipe. Creating the potential for complete failure/collapse of the pipe�as well as sizable sinkholes on the ground surface."));
        map.put("DNF",new CodeDTO("DNF", "Deposits Ingress Fine", " A fine deposit that has washed into the pipe such as silt or sand or peat etc."));
        map.put("DNGV",new CodeDTO("DNGV", "Deposits Ingress Gravel", " A deposit made up a larger particle of soil and gravel that have washed into the pipe e.g. coarse sediment. "));
        map.put("DNZ",new CodeDTO("DNZ", "Deposits Ingress Other", " A deposit of material that has washed into the pipe not suitably classified by any of the existing Deposits Ingress (DN) descriptors."));
        map.put("RF",new CodeDTO("RF", "Roots Fine", " Small diameter and small quantities of roots that are insufficient to cause a quantifiable reduction of pipe cross-sectional area (estimated to be less than 5%). Although these roots are not significant enough to have a measurable value the presence of fine roots are evidence that roots have found their way inside the pipe and may eventually grow and cause more extensive damage. Which may potentially cause future structural defects and flow obstructions in the pipeline. "));
        map.put("RFB",new CodeDTO("RFB", "Roots Fine Barrel", " Small diameter/small quantity (estimated to be less than 5%) roots that enters along the body/wall of the pipe."));
        map.put("RFC",new CodeDTO("RFC", "Roots Fine Connection", " Small diameter/small quantity (estimated to be less than 5%) roots that enter the pipe around the outside edge of a service pipe or tap at the interface between the lateral and the main pipe."));
        map.put("RFJ",new CodeDTO("RFJ", "Roots Fine Joint", " Small diameter/small quantity (estimated to be less than 5%) roots that are infiltrating into the pipe through a joint."));
        map.put("RFL",new CodeDTO("RFL", "Roots Fine Lateral", " Small diameter/small quantity (estimated to be less than 5%) roots enters the service pipe or lateral line�and continues into the sewer main."));
        map.put("RM",new CodeDTO("RM", "Roots Medium", " A formed root mass that is restricting flow. The root mass has caused a cross-sectional area of loss estimated to be between 5% and 50%."));
        map.put("RMB",new CodeDTO("RMB", "Roots Medium Barrel", " A mass of roots (estimated to be between 5% and 50%) enters along the body/wall of the pipe."));
        map.put("RMC",new CodeDTO("RMC", "Roots Medium Connection", " A mass of roots (estimated to be between 5% and 50%) enters the pipe around the outside edge of a service pipe or tap at the interface between the lateral and the main pipe."));
        map.put("RMJ",new CodeDTO("RMJ", "Roots Medium Joint", " A mass of roots (estimated to be between 5% and 50%) are infiltrating into the pipe through a joint. "));
        map.put("RML",new CodeDTO("RML", "Roots Medium Lateral", " A mass of roots (estimated to be between 5% and 50%) enters the service pipe or lateral line�and continues into the sewer main. "));
        map.put("RB",new CodeDTO("RB", "Roots Large Mass/Ball", " Roots have formed a large mass typically in the form of a ball�and have the potential to severely restrict flow. The root mass has caused a cross-sectional area of loss estimated to be greater than 50% (recorded as 55% or more). "));
        map.put("RBB",new CodeDTO("RBB", "Roots Large Mass/Ball Barrel", " A large mass of roots (estimated to be greater than 50%) enters along the body/wall of the pipe."));
        map.put("RBC",new CodeDTO("RBC", "Roots Large Mass/Ball Connection", " A large mass of roots (estimated to be greater than 50%) enters the pipe around the outside edge of a service pipe or tap at the interface between the lateral and the main pipe."));
        map.put("RBJ",new CodeDTO("RBJ", "Roots Large Mass/Ball Joint", " A large mass of roots (estimated to be greater than 50%) are infiltrating into the pipe through a joint. "));
        map.put("RBL",new CodeDTO("RBL", "Roots Large Mass/Ball Service Pipe", " A large mass of roots (estimated to be greater than 50%) enters the service pipe or lateral line�and continues into the sewer main."));
        map.put("RT",new CodeDTO("RT", "Roots Tap", " Individual roots that are estimated to be greater than 0.5 inch (10mm) in diameter. Tap roots are large enough to cause damage to the pipe material by expanding existing defects�and/or creating new defects."));
        map.put("RTB",new CodeDTO("RTB", "Roots Tap Barrel", " Individual roots that are estimated to be greater than � inch in diameter enters along the body/wall of the pipe."));
        map.put("RTC",new CodeDTO("RTC", "Roots Tap Connection", " Individual roots that are estimated to be greater than � inch in diameter enters the pipe around the outside edge of a service pipe or tap at the interface between the lateral and the main pipe."));
        map.put("RTJ",new CodeDTO("RTJ", "Roots Tap Joint", " Individual roots that are estimated to be greater than � inch in diameter are infiltrating into the pipe through a joint."));
        map.put("RTL",new CodeDTO("RTL", "Roots Tap Lateral", " Individual roots that are estimated to be greater than � inch in diameter enters the service pipe or lateral line�and continues into the sewer main."));
        map.put("IS",new CodeDTO("IS", "Infiltration Stain", " Though no moisture is currently present�there is discoloration that indicates prior water intrusion. Staining is different from Deposit Attached Encrustation (DAE) which is a buildup on the pipe wall"));
        map.put("ISB",new CodeDTO("ISB", "Infiltration Stain Barrel", " Infiltration stain is along the body/wall of the pipe."));
        map.put("ISC",new CodeDTO("ISC", "Infiltration Stain Connection", " Infiltration stain is around the outside edge of a service pipe�and/or at the tap/interface between the service pipe and the main sewer pipe. "));
        map.put("ISJ",new CodeDTO("ISJ", "Infiltration Stain Joint", " Infiltration stain is at the pipe joint. "));
        map.put("ISL",new CodeDTO("ISL", "Infiltration Stain Lateral", " Infiltration stain starts inside the service and/or lateral pipe�and continues to the sewer main."));
        map.put("IW",new CodeDTO("IW", "Infiltration Weeper", " Moisture on the pipe wall through a defect or faulty joint�through which groundwater slowly seeps into the pipe; observable flow is not typically visible."));
        map.put("IWB",new CodeDTO("IWB", "Infiltration Weeper Barrel", " There is moisture along the body/wall of the pipe."));
        map.put("IWC",new CodeDTO("IWC", "Infiltration Weeper Connection", " There is moisture around the outside edge of a service pipe and/or at the tap/interface between the service pipe and the main sewer pipe."));
        map.put("IWJ",new CodeDTO("IWJ", "Infiltration Weeper Joint", " There is moisture at the pipe joint."));
        map.put("IWL",new CodeDTO("IWL", "Infiltration Weeper Lateral", " Moisture starts inside the service and/or lateral pipe�and continues to the sewer main. "));
        map.put("IDB",new CodeDTO("IDB", "Infiltration Dripper Barrel", " A steady drip of water is entering through the body/wall of the pipe."));
        map.put("IDC",new CodeDTO("IDC", "Infiltration Dripper Connection", " A steady drip of water is entering around the outside edge of a service pipe and/or at the tap/interface between the service pipe and the main sewer pipe."));
        map.put("IDJ",new CodeDTO("IDJ", "Infiltration Dripper Joint", " A steady drip of water is entering through a pipe joint."));
        map.put("IDL",new CodeDTO("IDL", "Infiltration Dripper Lateral", " A steady drip of water is entering the service and/or lateral pipe�and continuing to the sewer main."));
        map.put("IR",new CodeDTO("IR", "Infiltration Runner", " A continuous flow of water intruding into the pipe through a defect pipe wall and/or faulty joint."));
        map.put("IRB",new CodeDTO("IRB", "Infiltration Runner Barrel", " A steady water stream is entering through the body/wall of the pipe."));
        map.put("IRC",new CodeDTO("IRC", "Infiltration Runner Connection", " A steady water stream is entering around the outside edge of a service pipe and/or at the tap/interface between the service pipe and the main sewer pipe."));
        map.put("IRJ",new CodeDTO("IRJ", "Infiltration Runner Joint", " A steady water stream is entering through a pipe joint."));
        map.put("IRL",new CodeDTO("IRL", "Infiltration Runner Lateral", " A steady water stream is entering the service and/or lateral pipe�and continuing to the sewer main."));
        map.put("IGB",new CodeDTO("IGB", "Infiltration Gusher Barrel", " Pressurized water is entering through the body/wall of the pipe."));
        map.put("IGC",new CodeDTO("IGC", "Infiltration Gusher Connection", " Pressurized water is entering around the outside edge of a service pipe and/or at the tap/interface between the service pipe and the main sewer pipe."));
        map.put("IGJ",new CodeDTO("IGJ", "Infiltration Gusher Joint", " Pressurized water is entering through a pipe joint."));
        map.put("IGL",new CodeDTO("IGL", "Infiltration Gusher Lateral", " Pressurized water is entering the service and/or lateral pipe�and continuing to the sewer main."));
        map.put("OBB",new CodeDTO("OBB", "Obstruction Brick or Masonry", " Bricks or other obvious masonry items that are causing a considerable reduction in available pipe area and/or obstructing flow. Typically these obstacles will be at or near the 600 position. Lying along the invert (bottom) of the pipe. "));
        map.put("OBC",new CodeDTO("OBC", "Obstruction Through Connection", " Foreign objects that have entered the pipe from/through a tie-in (connecting pipe)�and have become wedged at the connection. Examples my include wood or toys or pieces of metal�or any other kind of foreign object(s) that have been flushed down a toilet; washed into the line through a drain--or exterior storm line of a house on a combination sewer/sanitary sewer system. "));
        map.put("OBI",new CodeDTO("OBI", "Obstruction Intruding Through Wall", " Typically third party� objects that have been inserted after initial installation/construction of the pipeline. Examples may include utility poles grounding rods--and cross-bores such as gas or water or fiber optic or power and telephone lines. Damage to the pipe related to the intrusion should also be coded. NOTE- Cross-bores intruding through sewer lines pose significant hazards. All OBI�s should be reported to the appropriate authorities. "));
        map.put("OBJ",new CodeDTO("OBJ", "Obstruction Wedged in Joint", " Describes any foreign object that is wedged in the joint; not to be used to describe intruding gasket rings/seals�or sealing materials."));
        map.put("OBM",new CodeDTO("OBM", "Obstruction Pipe Material in Invert", " Large to medium sections of broken pipe lying at the invert (bottom) of pipe."));
        map.put("OBN",new CodeDTO("OBN", "Obstruction Construction Debris", " Debris such as loose pieces of concrete or metal or wood timbers found at/near the invert (bottom) of the pipe."));
        map.put("OBP",new CodeDTO("OBP", "Obstruction External Pipe or Cable", " Objects that have been inserted after the initial installation/construction of the pipe�or hoses/cables of cleaning equipment that became caught. Broke and/or cut and left in the line. "));
        map.put("OBR",new CodeDTO("OBR", "Obstruction Rocks", " Rock deposits larger than gravel that are obstructing flow. "));
        map.put("OBS",new CodeDTO("OBS", "Obstruction Built into Structure", " An object intentionally incorporated into the pipe structure to accommodate another pre-existing structure�such as another service pipe. Duct or building foundation built into a large pipeline or manhole/access chamber. "));
        map.put("OBZ",new CodeDTO("OBZ", "Obstruction Other", " Any obstacle in the pipeline not adequately classified by any other obstacle/obstructions (OB) descriptors�such as a plumber�s snake or sewer drain cable. "));
        map.put("TB",new CodeDTO("TB", "Tap Break-in/Hammer Tap", " A separate service/branch line has been inserted/connected through a rough hole in the pipe--made by cutting or chiseling and/or breaking the pipe wall, without the use of a fitting to connect and seal the tie-in/connection. Cutting or breaking into the wall of the pipe may compromise the structural integrity of the pipe material�potentially leading to continued fracturing/cracking of the pipe. The hole made in the pipe may not be sufficiently sealed and could allow for both infiltration and leaking. Additionally, if the connecting pipe is not sufficiently angled or protrudes too far into the line, it can affect proper operation of the line."));
        map.put("TBI",new CodeDTO("TBI", "Tap Break-In/Hamm Tap Intruding", " The break-in tap, or a portion of it intrudes into the pipe. Intruding break-in taps are a common. "));
        map.put("TBD",new CodeDTO("TBD", "Tap Break-In/Hamm Tap Defective", " The break-in tap that is defective. "));
        map.put("TBC",new CodeDTO("TBC", "Tap Break-In/Hamm Tap Capped", " The break-in tap has been blocked off or plugged (with a stopper or a cap). The cap is also referred to as a plug or a biscuit. "));
        map.put("TBA",new CodeDTO("TBA", "Tap Break-In/Hamm Tap Activity", " Flow was observed coming from the pipe/break-in tap."));
        map.put("TBB",new CodeDTO("TBB", "Tap Break-In/Hamm Tap Abandoned", " The break-in tap appears no longer be in use; collapsed or obstructed pipe is a possibility. "));
        map.put("TF",new CodeDTO("TF", "Tap Factory", " A purpose-made or pre-formed pipe tie-in fitting that was built into the sewer during installation/construction. Taps/tie-ins that have been installed by removing and replacing a portion of the original pipe with a tap fitting is also considered a Tap Factory (TF). "));
        map.put("TFI",new CodeDTO("TFI", "Tap Factory Intruding", " A portion of the connecting service/branch/lateral line intrudes into the pipe from a factory tap. "));
        map.put("TFD",new CodeDTO("TFD", "Tap Factory Defective", " The factory tap meets one or more of the defective tap conditions listed below --Intruding (I) Modifier The tap, or a portion of it intrudes into the pipe. --Defective (D) Modifier 1. Any defect observed inside the pipe. (A description of the defect should be included in the comments section). 2. A gap, separation and/or annular space is present in or around the tap. (If soil or void is visible in the gap, the Hole (H) descriptor should be used). 3. Infiltration and/or roots at the tap/connection. (Infiltration should be coded appropriately). 4. The tap is installed against the flow�pointing upstream� rather than downstream�. 5. The tap is smaller than the pipe it connects to and enters below normal water flow level�causing waste, wastewater and/or debris to accumulate as the flow from service/branch/lateral line flows into the main pipe. --Activity (A) Modifier) Flow through the tap/pipe was observed during the inspection. (If the flow can be confirmed as an infiltration defect inside the pipe, the tap should be recorded as defective instead of active, and the defect should be recorded in the comments section)."));
        map.put("TFC",new CodeDTO("TFC", "Tap Factory Capped", " The factory tap has been blocked off or plugged (with a stopper or cap). The cap is also referred to as a plug or biscuit. "));
        map.put("TFA",new CodeDTO("TFA", "Tap Factory Activity", " Flow from the factory tap was observed. (If a source of infiltration can be seen�the proper descriptor for Infiltration (I) should be used instead)."));
        map.put("TFB",new CodeDTO("TFB", "Tap Factory Abandoned", " The factory tap appears no longer be in use; collapsed or obstructed pipe is a possibility."));
        map.put("TR",new CodeDTO("TR", "Tap Rehabilitated", " A tap that has been repaired or rehabilitated using an internal repair method. "));
        map.put("TRI",new CodeDTO("TRI", "Tap Rehabilitated Intruding", " The rehabilitated tap�or a portion of it intrudes into the pipe. "));
        map.put("TRD",new CodeDTO("TRD", "Tap Rehabilitated Defective", " The rehabilitated tap includes one or more of the defective tap conditions listed below--Intruding (I) Modifier The tap, or a portion of it intrudes into the pipe. --Defective (D) Modifier 1. Any defect observed inside the pipe. (A description of the defect should be included in the comments section). 2. A gap, separation and/or annular space is present in or around the tap. (If soil or void is visible in the gap, the Hole (H) descriptor should be used). 3. Infiltration and/or roots at the tap/connection. (Infiltration should be coded appropriately). 4. The tap is installed against the flow�pointing upstream� rather than downstream�. 5. The tap is smaller than the pipe it connects to and enters below normal water flow level�causing waste, wastewater and/or debris to accumulate as the flow from service/branch/lateral line flows into the main pipe. --Activity (A) Modifier) Flow through the tap/pipe was observed during the inspection. (If the flow can be confirmed as an infiltration defect inside the pipe, the tap should be recorded as defective instead of active, and the defect should be recorded in the comments section). "));
        map.put("TRC",new CodeDTO("TRC", "Tap Rehabilitated Capped", " The rehabilitated tap has been blocked off or plugged (with a stopper or cap). The cap is also referred to as a plug or biscuit. "));
        map.put("TRA",new CodeDTO("TRA", "Tap Rehabilitated Activity", " Flow from the rehabilitated tap was observed. (If a source of infiltration can be seen�the proper descriptor for Infiltration (I) should be used instead). "));
        map.put("TRB",new CodeDTO("TRB", "Tap Rehabilitated Abandoned", " The rehabilitated tap appears no longer be in use; collapsed or obstructed pipe is a possibility."));
        map.put("TS",new CodeDTO("TS", "Tap Saddle", " A special fitting used to connect and seal a service, branch or main lateral line to the inside or outside wall of another waste line, lateral line or sewer main. Often found on lateral connections that have been made after the sewer main was installed, or on installed in pipelines that will not accommodate a factory-made tap�a saddle tap is preferable to break-in/hammer taps because of its use of a mechanical seal (straps or compression fittings), creating a fitted connection between the 2 (two) pipes. Saddle taps may also have seals that prevent both infiltration and exfiltration; saddle taps made of polyethylene are constructed by fusing pipes together creating a watertight connection without the need for straps or seals."));
        map.put("TSI",new CodeDTO("TSI", "Tap Saddle Intruding", " The tap Saddle�or a portion of it intrudes into the pipe."));
        map.put("TSD",new CodeDTO("TSD", "Tap Saddle Defective", " The tap saddle includes one or more of the defective tap conditions listed below --Intruding (I) Modifier The tap, or a portion of it intrudes into the pipe. --Defective (D) Modifier   1. Any defect observed inside the pipe. (A description of the defect should be included in the comments section). 2. A gap, separation and/or annular space is present in or around the tap. (If soil or void is visible in the gap, the Hole (H) descriptor should be used). 3. Infiltration and/or roots at the tap/connection. (Infiltration should be coded appropriately). 4. The tap is installed against the flow�pointing upstream� rather than downstream�. 5. The tap is smaller than the pipe it connects to and enters below normal water flow level�causing waste, wastewater and/or debris to accumulate as the flow from service/branch/lateral line flows into the main pipe.  --Activity (A) Modifier) Flow through the tap/pipe was observed during the inspection. (If the flow can be confirmed as an infiltration defect inside the pipe, the tap should be recorded as defective instead of active, and the defect should be recorded in the comments section). "));
        map.put("TSC",new CodeDTO("TSC", "Tap Saddle Capped", " The tap saddle has been blocked off or plugged (with a stopper or cap). The cap is also referred to as a plug or biscuit."));
        map.put("TSA",new CodeDTO("TSA", "Tap Saddle Activity", " Flow from the tap saddle was observed. (If a source of infiltration can be seen�the proper descriptor for Infiltration (I) should be used instead). "));
        map.put("TSB",new CodeDTO("TSB", "Tap Saddle Abandoned", " The tap saddle appears no longer be in use; collapsed or obstructed pipe is a possibility."));
        map.put("ESGT",new CodeDTO("ESGT", "Intruding Sealing Material Grout", " Grout, coal tar, concrete, cement, polymer, etc.�or other similar sealing material is visible at one or more joints. "));
        map.put("ISSRB",new CodeDTO("ISSRB", "Intruding Sealing Material/Sealing Ring Broken", " There is a visibly broken sealing ring/hub gasket at one or more joints. "));
        map.put("ISSRH",new CodeDTO("ISSRH", "Intruding Sealing Material/Sealing Ring Hanging", " There is a visibly slipped/pinched sealing ring/hub gasket at one or more joints. "));
        map.put("ISSRL",new CodeDTO("ISSRL", "Intruding Sealing Material/Sealing Ring Lose, Poorly Fitting", " There is an internally installed sealing ring/hub gasket not properly positioned or has shifted from its correct position�which does not appear to be sealing the joint. Though not it is not inside the pipe, it is visible in the joint."));
        map.put("ISZ",new CodeDTO("ISZ", "Intruding Sealing Material Other", " Visible material not covered in the other *Intruding Sealing Material (IS) descriptors. (Details should be provided in the comment section). "));
        map.put("LD",new CodeDTO("LD", "Line Down", " The pipe direction deviates down. "));
        map.put("LL",new CodeDTO("LL", "Line Left", " The pipe direction deviates to the left. "));
        map.put("LLD",new CodeDTO("LLD", "Line Down Left", " The pipe direction deviates to the left and down."));
        map.put("LLU",new CodeDTO("LLU", "Line Left Up", " The pipe direction deviates to the left and up. "));
        map.put("LR",new CodeDTO("LR", "Line Right", " The pipe direction deviates to the right. "));
        map.put("LRD",new CodeDTO("LRD", "Line Right Down", " The pipe direction deviates to the right and down. "));
        map.put("LRU",new CodeDTO("LRU", "Line Right Up", " The pipe direction deviates to the right and up. "));
        map.put("LU",new CodeDTO("LU", "Line Up", " The pipe direction deviates up."));
        map.put("ACO",new CodeDTO("ACO", "Access Point Interior Floor Cleanout", "  Provides access to the pipe(s) for maintenance and inspection. The interior floor cleanout is typically a 2.5� � 6� round, threaded cap (also referred to as a plug�) located in the floor of the basement, usually at/near the wall where the underground sewer line exits to the exterior of the structure."));
        map.put("AEO",new CodeDTO("AEO", "Access Point Exterior Cleanout", " Provides access to the pipe(s) for maintenance and inspection. The interior floor cleanout is typically a 3� � 6� round, threaded cap (also referred to as a plug�) located to the exterior of the structure. Though normally located at/near the exterior wall of the structure, there may also be cleanouts/additional cleanouts located further down the pipe, especially in longer lines."));
        map.put("AVO",new CodeDTO("AVO", "Access Point Vertical Stack Cleanout", " Provides access to the pipe(s) for maintenance and inspection. The vertical stack cleanout is typically a 2.5� � 6� round, threaded cap (also referred to as a plug�) located on the a basement vertical sewage pipe (stack) that runs from the vent at the roof,  to the connection to the sanitary line beneath the basement slab."));
        map.put("AHP",new CodeDTO("AHP", "Access Point Horizontal Pipe Cleanout", " Provides access to the pipe(s) for maintenance and inspection. The horizontal pipe cleanout is typically a 2.5� � 6� round, threaded cap (also referred to as a plug�) located in an above ground horizonal pipe that runs to and taps into the main sanitary line. "));
        map.put("AHO",new CodeDTO("AHO", "Access Point Hung Sewer Cleanout", " Provides access to the pipe(s) for maintenance and inspection. The hung sewer cleanout is typically a 3� � 4� round, threaded cap (also referred to as a plug�) normally located in the basement at the beginning of the horizontal mainline. Rather than exiting the house beneath the floor a hung sewer begins/exits through the foundation wall�above the basement slab."));
        map.put("ATO",new CodeDTO("ATO", "Access Point Toilet Flange", " The toilet flange access is usually located in the floor and is typically 3� �4�. Flanges may only be accessed by pulling/removing the toilet. Due to upper level toilets usually connecting to a vertical stack at a sharp 90-degree bend. The main sewer line may likely only be accessible through a basement toilet flange�which typically has a wide 90-degree sweep. "));
        map.put("ASO",new CodeDTO("ASO", "Access Point Soil Vent Stack", " The soil vent stack is normally located at the roof of the structure�and runs to the sanitary line beneath the basement slab. Typically vent stacks are only used when no other access point exists. "));
        map.put("AFD",new CodeDTO("AFD", "Access Point Floor Drain/Exterior Storm Drain", " Interior floor drains are typically located in the basement and/or garage slab; exterior storm drains may be located at the bottom of exterior stairs leading the basement in driveways or various other exterior locations. Floor/storm drains are usually trapped� requiring a specialized sewer camera designed to go through a trap�and is normally only effective for the visual inspection of the branch/service line only. "));
        map.put("ASP",new CodeDTO("ASP", "Access Point Sump Pit", " Sump pits are normally located in basement and/or garage slabs. In addition to pumping out subsurface ground water�floor and footer drains may also discharge into sump pits. "));
        map.put("ADS",new CodeDTO("ADS", "Access Point Downspout at Structure", " The downspout access is normally located at the base of exterior surface�and runs beneath the surface to an above ground discharge or main storm line. For houses that are on combination sanitary/storm sewer systems. The downspout line(s) typically run beneath the basement slab where they connect to the interior waste/sanitary line�or the sanitary line(s) run beneath the basement slab where they connect to the downspout line(s) that also run beneath the basement slab. Because downspout lines are usually trapped�the sanitary line may likely not be accessible through the downspout line. "));
        map.put("ADP",new CodeDTO("ADP", "Access Point Discharge Point", " The location where a pipe (usually a downspout or storm line) discharges/daylights on the surface; the discharge point may include (but not limited to) a curb cut-out creek�or any other low spot surrounding the structure where the water may be properly irrigated. "));
        map.put("AST",new CodeDTO("AST", "Access Point Septic Tank", " Septic tanks are located beneath the exterior surface�and can be used to access both the septic sanitary line�as well as the septic discharge line."));
        map.put("AJB",new CodeDTO("AJB", "Access Point Junction Box", " A chamber constructed to evenly distribute liquid waste of an anaerobic septic system from the main tank/discharge line to a leach field (also referred to as a drain field) through a network of 2 or more perforated pipes�referred to as leach lines. "));
        map.put("ACB",new CodeDTO("ACB", "Access Point Catch Basin", " An inlet or other access structure that allows inflow collection and draining of storm water to the discharge�normally on the surface or to a main storm sewer. "));
        map.put("AMH",new CodeDTO("AMH", "Access Point Manhole", " A man-entry structure designed to provide access to the pipes for maintenance and inspection."));
        map.put("AZ",new CodeDTO("AZ", "Access Point Other", " Any other access points not described in other descriptors. Describe the type of access point in the comments section."));
        map.put("MCU",new CodeDTO("MCU", "Miscellaneous Camera Submerged", " The camera lens is completely submerged beneath wastewater/sewage."));
        map.put("MGO",new CodeDTO("MGO", "Miscellaneous General Observation", " General Observation Comment."));
        map.put("MLC",new CodeDTO("MLC", "Miscellaneous Lining Change", " The lining or coating material in the pipe has changed."));
        map.put("MMC",new CodeDTO("MMC", "Miscellaneous Material Change", " The pipe material has changed/transitioned."));
        map.put("MSC",new CodeDTO("MSC", "Miscellaneous Shape/Size Change", " The pipe shape and/or dimension has changed."));
        map.put("MWL",new CodeDTO("MWL", "Miscellaneous Water Level", " Indicates the water depth/volume at an observed point in the pipe�including both flowing and static water; to be measured from the invert (bottom of the pipe) to the water surface."));
        map.put("MSA",new CodeDTO("MSA", "Miscellaneous Survey Abandoned", " Indicates that the inspection/survey was unable to be completed. Inspections/survey may be abandoned due to Blockage by an intruding connection an obstruction�heavy/severe debris buildup and/or collapsed pipe."));
        map.put("MWLS",new CodeDTO("MWLS", "Miscellaneous Water Level Sag", " Due to ground/soil conditions and/or inferior/improper installation the section of the line has sunk creating a sag also referred to as a dip low spot�or valley�. This restricted flow can allow pape, waste and debris to collect and build up in the pipe creating the potential for soft clog� backups."));
        map.put("MWM",new CodeDTO("MWM", "Miscellaneous Water Mark", " What appear to be water marks--or water lines on the side and/or near the top of the pipe is possible evidence of (a) prior restrictions/sewage backup(s) in the line. "));
        map.put("MY",new CodeDTO("MY", "Miscellaneous Dye Test", " The survey and dye test were performed simultaneously."));
        map.put("MYN",new CodeDTO("MYN", "Miscellaneous Dye Test Not Visible", " The dye test was conducted but no dye was observed in the pipe."));

        for(CodeDTO cd : map.values()){
            System.out.println(cd.getName());
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
