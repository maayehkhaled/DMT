package com.qpros.quanta;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.qpros.quanta.exceptions.GherkinKeywordNotFoundException;
import com.qpros.quanta.gherkin.GherkinDialect;
import com.qpros.quanta.gherkin.GherkinDialectProvider;
import com.qpros.quanta.gherkin.model.Asterisk;
import com.qpros.quanta.gherkin.model.IGherkinFormatterModel;

import freemarker.template.utility.StringUtil;

/**
 * Allows {@link IGherkinFormatterModel} to be returned by using a name, from the below gherkin model classes:
 * 
 * <ul>
 * 	<li>{@link com.qpros.quanta.gherkin.model.Feature}</li>
 * 	<li>{@link com.qpros.quanta.gherkin.model.Background}</li>
 * 	<li>{@link com.qpros.quanta.gherkin.model.Scenario}</li>
 * 	<li>{@link com.qpros.quanta.gherkin.model.Given}</li>
 * 	<li>{@link com.qpros.quanta.gherkin.model.When}</li>
 * 	<li>{@link com.qpros.quanta.gherkin.model.Then}</li>
 * 	<li>{@link com.qpros.quanta.gherkin.model.And}</li>
 * </ul>
 * 
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * Quanta.createTest(new GherkinKeyword("Feature"), "bddTest");
 * test.createNode(new GherkinKeyword("Scenario"), bddNode");
 * </pre>
 * 
 * @see IGherkinFormatterModel
 */
public class GherkinKeyword {

	private static final Logger logger = Logger.getLogger(GherkinKeyword.class.getName());
    
    private Class<IGherkinFormatterModel> clazz = IGherkinFormatterModel.class;
    private IGherkinFormatterModel keywordClazz;
    
    public GherkinKeyword(String keyword) throws ClassNotFoundException {    	
    	GherkinDialect dialect =  null;
        String apiKeyword = StringUtil.capitalize(keyword.trim());
        String refPath = clazz.getPackage().getName();
        
        try {
        	apiKeyword = apiKeyword.equals("*") ? Asterisk.class.getSimpleName() : apiKeyword;
        	dialect = GherkinDialectProvider.getDialect();
            if (dialect != null && !dialect.getLanguage().equalsIgnoreCase(GherkinDialectProvider.getDefaultLanguage())) {
                apiKeyword = null;
                Map<String, List<String>> keywords = dialect.getKeywords();
                
                for (Entry<String, List<String>> key : keywords.entrySet()) {
                    boolean keywordLocated = key.getValue()
                			.stream()
                			.anyMatch(x -> x.trim().equalsIgnoreCase(keyword.trim()));
                    if (keywordLocated) {
                        apiKeyword = StringUtil.capitalize(key.getKey());
                        break;
                    }
                }
            }
            
            if (apiKeyword == null) {
            	throw new GherkinKeywordNotFoundException("Keyword " + apiKeyword + " cannot be null");
            }
            
            String clazzName = refPath + "." + apiKeyword.replace(" ", "");
            Class<?> c = Class.forName(clazzName);
            keywordClazz = (IGherkinFormatterModel) c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.log(Level.SEVERE, "", e);
        }
    }
    
    IGherkinFormatterModel getKeyword() {
        return keywordClazz;
    }
}
