package example;

import org.aksw.mex.MEXModel;
import org.aksw.mex.MyMEX_10;
import org.aksw.mex.util.Global;
import org.aksw.mex.util.Global.*;

import java.util.Date;

/**
 * Created by esteves on 27.06.15.
 */
public class ExampleSimple {

    public static void main(String[] args) {

        //the MEX wrapper!
        MyMEX_10 mex = new MyMEX_10();

        //basic authoring provenance
        {
            //change later here the sets for adds
            mex.setAuthorName("D Esteves");
            mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
            mex.setContext(Global.EnumContext.RecomenderSystems);
            mex.setOrganization("Leipzig University");

            mex.setExp_Id("E001");
            mex.setExp_Title("my first experiment");
            mex.setExp_Date(new Date());
            mex.setExp_Description("my first MEX experiment");
        }

        String eid = "E001S001";
        //ml-experiment-configuration
        {
            mex.addExpConf_Id(eid);

            mex.getExpConf(eid).set_description("analyzing 10-fold cross validation for SVM");
            mex.getExpConf(eid).getModel().set_id("svm20150322");
            mex.getExpConf(eid).getModel().set_description("model generated by LibSVM");
            mex.getExpConf(eid).getModel().set_date(new Date());

            mex.getExpConf(eid).getSamplingMethod().setName(EnumSamplingMethod.CrossValidation);
            mex.getExpConf(eid).getSamplingMethod().setTrainSize(0.8);
            mex.getExpConf(eid).getSamplingMethod().setTestSize(0.2);
            mex.getExpConf(eid).getSamplingMethod().setFolds(10);
            mex.getExpConf(eid).getSamplingMethod().setSequential(true);

            mex.getExpConf(eid).getHardwareConfiguration().setOperationalSystem("ubuntu");
            mex.getExpConf(eid).getHardwareConfiguration().setCPU(EnumProcessor.INTEL_COREI7);
            mex.getExpConf(eid).getHardwareConfiguration().setMemory(EnumRandomAccessMemory.SIZE_16GB);
            mex.getExpConf(eid).getHardwareConfiguration().setHD("SSD");
            mex.getExpConf(eid).getHardwareConfiguration().setCache(EnumCache.CACHE_3MB);

            mex.getExpConf(eid).getDataSet().setName("bovespa");
            mex.getExpConf(eid).getDataSet().setDescription("brazilian stock market 2013");
            mex.getExpConf(eid).getDataSet().setURI("http://www.bmfbovespa.com.br/shared/iframe.aspx?idioma=pt-br&url=http://www.bmfbovespa.com.br/pt-br/cotacoes-historicas/FormSeriesHistoricas.asp");

            mex.getExpConf(eid).addFeature("min");
            mex.getExpConf(eid).addFeature("max");
            mex.getExpConf(eid).addFeature("ope");
            mex.getExpConf(eid).addFeature("clo");

        }

        //adding algorithms and parameters
        {
            mex.getExpConf(eid).getImplementation().set(EnumImplementation.Weka);
            mex.getExpConf(eid).getImplementation().setRevision("3.6.6");

            mex.getExpConf(eid).addAlgorithm(EnumAlgorithm.SupportVectorMachines);
            mex.getExpConf(eid).addAlgorithm(EnumAlgorithm.NaiveBayes);

            mex.getExpConf(eid).getAlgorithm(EnumAlgorithm.SupportVectorMachines).addParameter("C", "10^3");
            mex.getExpConf(eid).getAlgorithm(EnumAlgorithm.SupportVectorMachines).addParameter("alpha", "0.2");
        }


        //associate your run x each algorithm
        {
            String ex1 = "EX001";
            String ex2 = "EX002";

            mex.getExpConf(eid).addExecutionOverall(ex1, EnumPhase.TRAIN);
            mex.getExpConf(eid).getExecution(ex1).setStartDate(new Date());
            mex.getExpConf(eid).getExecutionOverall(ex1).setStartsAtPosition("1233");
            mex.getExpConf(eid).getExecutionOverall(ex1).setEndsAtPosition("1376");
            //your models call here !
            mex.getExpConf(eid).getExecution(ex1).setEndDate(new Date());


            mex.getExpConf(eid).addExecutionOverall(ex2, EnumPhase.TEST);
            mex.getExpConf(eid).getExecution(ex2).setStartDate(new Date());
            mex.getExpConf(eid).getExecutionOverall(ex2).setStartsAtPosition("1377");
            mex.getExpConf(eid).getExecutionOverall(ex2).setEndsAtPosition("1420");
            //your models call here !
            mex.getExpConf(eid).getExecution(ex2).setEndDate(new Date());

        }


        //saving performances for each run



        //exporting your ML experiment
        MEXModel.getInstance().parse();
        MEXModel.getInstance().saveToDisk("teste.ttl","http://mex.aksw.org/examples/001/", mex);

        System.exit(0);

    }

}
