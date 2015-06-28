package org.aksw.mex.core;

import org.aksw.mex.util.Global;
import org.aksw.mex.util.ontology.mex.MEXCORE_10;
import org.aksw.mex.util.ontology.PROVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by root on 25.06.15.
 */
public class ApplicationContextVO  {

    public Date get_fileDate() {return _fileDate;}

    public String get_givenName() {
        return _givenName;
    }

    public String get_mbox() {
        return _mbox;
    }

    public String get_homepage() {
        return _homepage;
    }

    public String get_description() {
        return _description;
    }

    public String get_category() {
        return _category;
    }

    public String get_location() {
        return _location;
    }

    public String get_trustyURI() {
        return _trustyURI;
    }

    private String _givenName;
    private String _mbox;
    private String _homepage;
    private String _description;
    private String _category;
    private String _location;
    private String _trustyURI;
    private Date _fileDate;

    private ContextVO _context;
    private List<ExperimentVO> _experiments;

    public ApplicationContextVO(){
        this._fileDate = new Date();
        this._context = new ContextVO(Global.EnumContext.NotInformed);
    }

    public ApplicationContextVO(String name, String mbox){
        this._fileDate = new Date();
        this._givenName = name;
        this._mbox = mbox;
        this._context = new ContextVO(Global.EnumContext.NotInformed);
        this._experiments = new ArrayList<>();
        this._fileDate = new Date();
    }


    public ApplicationContextVO(String givenName, String mbox, String homepage, String description, String category, String location, String trustyURI) {
        this._givenName = givenName;
        this._mbox = mbox;
        this._homepage = homepage;
        this._description = description;
        this._category = category;
        this._location = location;
        this._trustyURI = trustyURI;
        this._context = new ContextVO(Global.EnumContext.NotInformed);
    }

    public void setContext(String value){
        this._context.setContext(value);
    }
    public void setAuthorName(String value){
        this._givenName = value;
    }
    public void setMailBox(String value){
        this._mbox = value;
    }
    public void setHomepage(String value){
        this._homepage = value;
    }
    public void setDescription(String value){
        this._description = value;
    }
    public void setCategory(String value){
        this._category = value;
    }
    public void setLocation(String value){
        this._location = value;
    }
    public void setTrustyURI(String value){
        this._trustyURI = value;
    }


    public boolean addExperiment(ExperimentVO value){
        return this._experiments.add(value);
    }

    public String getClassName(){
        return "ApplicationContext";
    }
    public String getMEXPrefixNamespace(){
        return MEXCORE_10.ClasseTypes.APPLICATION_CONTEXT;
    }

    public String getPROVClassName(){
        return PROVO.ClasseTypes.AGENT;
    }


}