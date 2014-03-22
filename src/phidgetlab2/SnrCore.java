/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgetlab2;

import com.phidgets.*;
import com.phidgets.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 *
 * @author sandeepnarwal
 */
public class SnrCore extends PhiCore{
 
    int snrIndex, snrValue;
	
	// must define all sensor port number
	// whenever sensors are attached
    static int SNR_300_ROTATOR = 3;
    static int SNR_LIGHT_1 = 0;

	static int SNR_IR_DISTANCE = 7;
	static int SNR_LIGHT_2 = 5;

	// all sensor object goes here
	// each for above 
    Snr300Rotator ObjSnr300Rotator;
    SnrIrDistance ObjSnrIrDistance;
    SnrLight1 ObjSnrLight1;
	SnrLight2 ObjSnrLight2;

	/*
	TODO later use hashmap instead of this array
	*/
	//int connectedSnr = {  };
    
    // sensitivity level, can be from 0 - 1000
    // lower the number higher the sensitivity
    static int SENSITIVE_LEVEL_1 = 900;
    static int SENSITIVE_LEVEL_2 = 700;
    static int SENSITIVE_LEVEL_3 = 550;
    static int SENSITIVE_LEVEL_4 = 350;
    static int SENSITIVE_LEVEL_5 = 200;
    static int SENSITIVE_LEVEL_6 = 100;
    static int SENSITIVE_LEVEL_7 = 50;
    static int SENSITIVE_LEVEL_8 = 20;
    static int SENSITIVE_LEVEL_9 = 1;
    
	LiveChart spanel;
	HighPassFilter ObjHPFilter;
	
	Http ObjHttp;
	
	double threshHoldMin;
	double threshHoldMax;
	
	double hpF;
	
	double triggerVal = 200;
	boolean lightsOn = false;

	
    public SnrCore(){
		

		/*
		TODO refine it, this should contain the list of all sensors
		*/

		/*
		Hashtable<Integer, String> source = new Hashtable<Integer,String>();
		HashMap<Integer, String>  map = new HashMap(source);
		map.put(0, "SNR_300_RORATOR");
		*/
		
	}
	
    /**
     *
     * @param Snr
     * @param level
     * @throws PhidgetException
     */
    public void setSensorSensitivity(int Snr, int level) throws PhidgetException {    
        
		// this is where any sensor change any sensitivity of any other sensor
		// depends what object is using this call
        ObjPhiKit.setSensorChangeTrigger(Snr,level);
    }

    
    /**
     *  
     */
    public void initSensors(){
        
		ObjHPFilter = new HighPassFilter();
		
		ObjHttp = new Http();
		
		spanel = new LiveChart();
		spanel.initParticle();
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(spanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 512);
		frame.setVisible(true);
		
		System.out.println("init spanel");
		
		threshHoldMin = 300;
		threshHoldMax = 800;

		// sensor change even lister
		// from phidget API
        ObjPhiKit.addSensorChangeListener(new SensorChangeListener(){

            @Override
            public void sensorChanged(SensorChangeEvent ChangedSnr) {
                
				/*
				TODO use for loop to auto set values
				put all sensors in an array of objects and loop through each
				adding a sensor should be as simple as adding into array and assigning the port num
				*/
                
				int currentIndex = ChangedSnr.getIndex();
                int currentValue = ChangedSnr.getValue();
                
                if(currentIndex == SNR_300_ROTATOR){
					ObjSnr300Rotator = new Snr300Rotator();
					ObjSnr300Rotator.initialize(currentValue);
					
					//spanel.estimate(currentValue);
					//spanel.estimate((int) ObjHPFilter.getFilter(currentValue,0.80));

                }
                
				if(currentIndex == SNR_LIGHT_1){
					ObjSnrLight1 = new SnrLight1();
					ObjSnrLight1.initialize(currentValue);
					double newValue;
					
					if(currentValue > threshHoldMin && currentValue < threshHoldMax){	
						//newValue = ((double)currentValue)/1024.0;
					
						//hpF = (double) ObjHPFilter.getFilter(newValue,0.5);
						//System.out.println(hpF);

						//spanel.estimate(currentValue);
						//spanel.estimate(hpF);
					}
				}

				if(currentIndex == SNR_IR_DISTANCE){
					ObjSnrIrDistance = new SnrIrDistance();
					ObjSnrIrDistance.initialize(currentValue);
					
					double newValue;
					newValue = currentValue;
						
					// tick
					//hpF = (double) ObjHPFilter.getFilter(newValue,0.5);

					if (newValue > triggerVal && lightsOn == false) {

						//System.out.println(" +++ new value - "+newValue);
						try {
							URL u = new URL("http://10.0.0.7/?u=/control/json/dmx/set_fix/hals,1:160,10:186,11:145,12:160,2:160,3:160,4:160,5:160,6:160,7:0,8:0,9:149,leds,101:0-0-255,103:0-0-255,105:0-0-255,107:0-0-255,109:0-0-255,111:0-0-255,125:0-0-255,127:0-0-255,129:0-0-255,131:0-0-255,133:0-0-255,135:0-0-255,137:0-0-255,139:0-0-255,141:0-0-255,143:0-0-255,145:0-0-255,147:0-0-255,163:0-0-255,165:0-0-255,167:0-0-255,169:0-0-255,171:0-0-255,173:0-0-255,175:0-0-255,177:0-0-255,179:0-0-255,181:0-0-255,183:0-0-255,185:0-0-255,187:0-0-255,189:0-0-255,191:0-0-255,193:0-0-255,195:0-0-255,199:0-0-255,201:0-0-255,203:0-0-255,205:0-0-255,207:0-0-255,209:0-0-255,211:0-0-255,213:0-0-255,215:0-0-255,217:0-0-255,219:0-0-255,221:0-0-255,223:0-0-255,225:0-0-255,227:0-0-255,229:0-0-255,231:0-0-255,233:0-0-255,235:0-0-255,237:0-0-255,239:0-0-255,241:0-0-255,243:0-0-255,245:0-0-255,247:0-0-255,249:0-0-255,251:0-0-255,253:0-0-255,255:0-0-255,257:0-0-255,259:0-0-255,261:0-0-255,263:0-0-255,265:0-0-255,267:0-0-255,269:0-0-255,273:0-0-255,275:0-0-255,277:0-0-255,279:0-0-255,281:0-0-255,283:0-0-255,285:0-0-255,287:0-0-255,289:0-0-255,291:0-0-255,293:0-0-255,295:0-0-255,297:0-0-255,299:0-0-255,301:0-0-255,303:0-0-255,305:0-0-255,307:0-0-255,309:0-0-255,311:0-0-255,313:0-0-255,315:0-0-255,317:0-0-255,319:0-0-255,321:0-0-255,323:0-0-255,325:0-0-255,327:0-0-255,329:0-0-255,331:0-0-255,333:0-0-255,335:0-0-255,337:0-0-255,339:0-0-255,341:0-0-255,343:0-0-255,347:0-0-255,349:0-0-255,351:0-0-255,353:0-0-255,355:0-0-255,357:0-0-255,359:0-0-255,361:0-0-255,363:0-0-255,365:0-0-255,367:0-0-255,369:0-0-255,37:0-0-255,371:0-0-255,373:0-0-255,375:0-0-255,377:0-0-255,379:0-0-255,381:0-0-255,383:0-0-255,385:0-0-255,387:0-0-255,389:0-0-255,391:0-0-255,393:0-0-255,395:0-0-255,397:0-0-255,399:0-0-255,401:0-0-255,403:0-0-255,405:0-0-255,407:0-0-255,409:0-0-255,411:0-0-255,413:0-0-255,415:0-0-255,417:0-0-255,421:0-0-255,423:0-0-255,425:0-0-255,427:0-0-255,429:0-0-255,431:0-0-255,433:0-0-255,435:0-0-255,437:0-0-255,439:0-0-255,441:0-0-255,443:0-0-255,445:0-0-255,447:0-0-255,449:0-0-255,451:0-0-255,453:0-0-255,455:0-0-255,457:0-0-255,459:0-0-255,461:0-0-255,463:0-0-255,465:0-0-255,467:0-0-255,469:0-0-255,471:0-0-255,473:0-0-255,475:0-0-255,477:0-0-255,479:0-0-255,481:0-0-255,483:0-0-255,485:0-0-255,487:0-0-255,489:0-0-255,491:0-0-255,495:0-0-255,497:0-0-255,499:0-0-255,501:0-0-255,503:0-0-255,505:0-0-255,507:0-0-255,509:0-0-255,51:0-0-255,511:0-0-255,513:0-0-255,515:0-0-255,517:0-0-255,519:0-0-255,521:0-0-255,523:0-0-255,525:0-0-255,527:0-0-255,529:0-0-255,53:0-0-255,531:0-0-255,533:0-0-255,535:0-0-255,537:0-0-255,541:0-0-255,543:0-0-255,55:0-0-255,557:0-0-255,559:0-0-255,561:0-0-255,563:0-0-255,565:0-0-255,569:0-0-255,57:0-0-255,571:0-0-255,573:0-0-255,577:0-0-255,579:0-0-255,581:0-0-255,59:0-0-255,593:0-0-255,595:0-0-255,597:0-0-255,599:0-0-255,601:0-0-255,603:0-0-255,605:0-0-255,607:0-0-255,609:0-0-255,61:0-0-255,611:0-0-255,613:0-0-255,615:0-0-255,617:0-0-255,63:0-0-255,631:0-0-255,633:0-0-255,635:0-0-255,637:0-0-255,639:0-0-255,643:0-0-255,645:0-0-255,647:0-0-255,649:0-0-255,65:0-0-255,651:0-0-255,653:0-0-255,655:0-0-255,667:0-0-255,669:0-0-255,67:0-0-255,671:0-0-255,673:0-0-255,675:0-0-255,677:0-0-255,679:0-0-255,681:0-0-255,683:0-0-255,685:0-0-255,687:0-0-255,689:0-0-255,69:0-0-255,691:0-0-255,705:0-0-255,707:0-0-255,709:0-0-255,71:0-0-255,711:0-0-255,713:0-0-255,717:0-0-255,719:0-0-255,721:0-0-255,723:0-0-255,725:0-0-255,727:0-0-255,729:0-0-255,73:0-0-255,741:0-0-255,743:0-0-255,745:0-0-255,747:0-0-255,749:0-0-255,751:0-0-255,753:0-0-255,755:0-0-255,757:0-0-255,759:0-0-255,761:0-0-255,763:0-0-255,765:0-0-255,779:0-0-255,781:0-0-255,783:0-0-255,785:0-0-255,787:0-0-255,791:0-0-255,793:0-0-255,795:0-0-255,797:0-0-255,799:0-0-255,801:0-0-255,803:0-0-255,827:0-0-255,829:0-0-255,831:0-0-255,833:0-0-255,835:0-0-255,837:0-0-255,839:0-0-255,865:0-0-255,867:0-0-255,869:0-0-255,871:0-0-255,873:0-0-255,875:0-0-255,877:0-0-255,89:0-0-255,903:0-0-255,905:0-0-255,907:0-0-255,909:0-0-255,91:0-0-255,913:0-0-255,915:0-0-255,93:0-0-255,95:0-0-255,97:0-0-255,99:0-0-255");
							ObjHttp.get(u);
						} catch (MalformedURLException ex) {
							System.out.println(" 1 " + newValue);
						} catch (IOException ex) {
							System.out.println(" 2 " + newValue);
						}

						lightsOn = true;

					} else if (newValue < triggerVal && lightsOn == true) {

						try {
							URL u = new URL("http://10.0.0.7/?u=/control/json/dmx/set_fix/hals,1:160,10:186,11:145,12:160,2:160,3:160,4:160,5:160,6:160,7:0,8:0,9:149,leds,101:0-255-0,103:0-255-0,105:0-255-0,107:0-255-0,109:0-255-0,111:0-255-0,125:0-255-0,127:0-255-0,129:0-255-0,131:0-255-0,133:0-255-0,135:0-255-0,137:0-255-0,139:0-255-0,141:0-255-0,143:0-255-0,145:0-255-0,147:0-255-0,163:0-255-0,165:0-255-0,167:0-255-0,169:0-255-0,171:0-255-0,173:0-255-0,175:0-255-0,177:0-255-0,179:0-255-0,181:0-255-0,183:0-255-0,185:0-255-0,187:0-255-0,189:0-255-0,191:0-255-0,193:0-255-0,195:0-255-0,199:0-255-0,201:0-255-0,203:0-255-0,205:0-255-0,207:0-255-0,209:0-255-0,211:0-255-0,213:0-255-0,215:0-255-0,217:0-255-0,219:0-255-0,221:0-255-0,223:0-255-0,225:0-255-0,227:0-255-0,229:0-255-0,231:0-255-0,233:0-255-0,235:0-255-0,237:0-255-0,239:0-255-0,241:0-255-0,243:0-255-0,245:0-255-0,247:0-255-0,249:0-255-0,251:0-255-0,253:0-255-0,255:0-255-0,257:0-255-0,259:0-255-0,261:0-255-0,263:0-255-0,265:0-255-0,267:0-255-0,269:0-255-0,273:0-255-0,275:0-255-0,277:0-255-0,279:0-255-0,281:0-255-0,283:0-255-0,285:0-255-0,287:0-255-0,289:0-255-0,291:0-255-0,293:0-255-0,295:0-255-0,297:0-255-0,299:0-255-0,301:0-255-0,303:0-255-0,305:0-255-0,307:0-255-0,309:0-255-0,311:0-255-0,313:0-255-0,315:0-255-0,317:0-255-0,319:0-255-0,321:0-255-0,323:0-255-0,325:0-255-0,327:0-255-0,329:0-255-0,331:0-255-0,333:0-255-0,335:0-255-0,337:0-255-0,339:0-255-0,341:0-255-0,343:0-255-0,347:0-255-0,349:0-255-0,351:0-255-0,353:0-255-0,355:0-255-0,357:0-255-0,359:0-255-0,361:0-255-0,363:0-255-0,365:0-255-0,367:0-255-0,369:0-255-0,37:0-255-0,371:0-255-0,373:0-255-0,375:0-255-0,377:0-255-0,379:0-255-0,381:0-255-0,383:0-255-0,385:0-255-0,387:0-255-0,389:0-255-0,391:0-255-0,393:0-255-0,395:0-255-0,397:0-255-0,399:0-255-0,401:0-255-0,403:0-255-0,405:0-255-0,407:0-255-0,409:0-255-0,411:0-255-0,413:0-255-0,415:0-255-0,417:0-255-0,421:0-255-0,423:0-255-0,425:0-255-0,427:0-255-0,429:0-255-0,431:0-255-0,433:0-255-0,435:0-255-0,437:0-255-0,439:0-255-0,441:0-255-0,443:0-255-0,445:0-255-0,447:0-255-0,449:0-255-0,451:0-255-0,453:0-255-0,455:0-255-0,457:0-255-0,459:0-255-0,461:0-255-0,463:0-255-0,465:0-255-0,467:0-255-0,469:0-255-0,471:0-255-0,473:0-255-0,475:0-255-0,477:0-255-0,479:0-255-0,481:0-255-0,483:0-255-0,485:0-255-0,487:0-255-0,489:0-255-0,491:0-255-0,495:0-255-0,497:0-255-0,499:0-255-0,501:0-255-0,503:0-255-0,505:0-255-0,507:0-255-0,509:0-255-0,51:0-255-0,511:0-255-0,513:0-255-0,515:0-255-0,517:0-255-0,519:0-255-0,521:0-255-0,523:0-255-0,525:0-255-0,527:0-255-0,529:0-255-0,53:0-255-0,531:0-255-0,533:0-255-0,535:0-255-0,537:0-255-0,541:0-255-0,543:0-255-0,55:0-255-0,557:0-255-0,559:0-255-0,561:0-255-0,563:0-255-0,565:0-255-0,569:0-255-0,57:0-255-0,571:0-255-0,573:0-255-0,577:0-255-0,579:0-255-0,581:0-255-0,59:0-255-0,593:0-255-0,595:0-255-0,597:0-255-0,599:0-255-0,601:0-255-0,603:0-255-0,605:0-255-0,607:0-255-0,609:0-255-0,61:0-255-0,611:0-255-0,613:0-255-0,615:0-255-0,617:0-255-0,63:0-255-0,631:0-255-0,633:0-255-0,635:0-255-0,637:0-255-0,639:0-255-0,643:0-255-0,645:0-255-0,647:0-255-0,649:0-255-0,65:0-255-0,651:0-255-0,653:0-255-0,655:0-255-0,667:0-255-0,669:0-255-0,67:0-255-0,671:0-255-0,673:0-255-0,675:0-255-0,677:0-255-0,679:0-255-0,681:0-255-0,683:0-255-0,685:0-255-0,687:0-255-0,689:0-255-0,69:0-255-0,691:0-255-0,705:0-255-0,707:0-255-0,709:0-255-0,71:0-255-0,711:0-255-0,713:0-255-0,717:0-255-0,719:0-255-0,721:0-255-0,723:0-255-0,725:0-255-0,727:0-255-0,729:0-255-0,73:0-255-0,741:0-255-0,743:0-255-0,745:0-255-0,747:0-255-0,749:0-255-0,751:0-255-0,753:0-255-0,755:0-255-0,757:0-255-0,759:0-255-0,761:0-255-0,763:0-255-0,765:0-255-0,779:0-255-0,781:0-255-0,783:0-255-0,785:0-255-0,787:0-255-0,791:0-255-0,793:0-255-0,795:0-255-0,797:0-255-0,799:0-255-0,801:0-255-0,803:0-255-0,827:0-255-0,829:0-255-0,831:0-255-0,833:0-255-0,835:0-255-0,837:0-255-0,839:0-255-0,865:0-255-0,867:0-255-0,869:0-255-0,871:0-255-0,873:0-255-0,875:0-255-0,877:0-255-0,89:0-255-0,903:0-255-0,905:0-255-0,907:0-255-0,909:0-255-0,91:0-255-0,913:0-255-0,915:0-255-0,93:0-255-0,95:0-255-0,97:0-255-0,99:0-255-0");
							ObjHttp.get(u);
						} catch (MalformedURLException ex) {
							System.out.println(" 11 " + newValue);
						} catch (IOException ex) {
							System.out.println(" 21 " + newValue);
						}
						lightsOn = false;

					}

					spanel.estimate(currentValue);
					//spanel.estimate(hpF);
					
                }
				
				if(currentIndex == SNR_LIGHT_2){
					ObjSnrLight2 = new SnrLight2();
					ObjSnrLight2.initialize(currentValue);
                }
				
            }
        
        });
        
    }

	/**
     *
     * @param val
     */
    public void setValue(int val){
        snrValue = val;
    }
    
    /**
     *
     */
    public int getValue(){
		
		return snrValue;
    }
    
    /**
     *
     */
    public void printValue(){
        System.out.println(snrValue);
    }
 

}
