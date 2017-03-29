package io.swagger.codegen.languages;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import io.swagger.codegen.*;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.MapProperty;
import io.swagger.models.properties.Property;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Swift3Codegen extends AbstractSwift3Codegen {

    public static final String RESPONSE_AS = "responseAs";
    protected static final String LIBRARY_PROMISE_KIT = "PromiseKit";
    protected static final String LIBRARY_RX_SWIFT = "RxSwift";

    protected static final String[] RESPONSE_LIBRARIES = {LIBRARY_PROMISE_KIT, LIBRARY_RX_SWIFT};
    protected String[] responseAs = new String[0];

    @Override
    public String getName() {
        return "swift3";
    }

    public Swift3Codegen() {
        super();

        reservedWords.add("ErrorResponse");

        cliOptions.add(new CliOption(RESPONSE_AS, "Optionally use libraries to manage response.  Currently " +
                StringUtils.join(RESPONSE_LIBRARIES, ", ") + " are available."));
    }

    @Override
    public void processOpts() {
        super.processOpts();

        // Setup unwrapRequired option, which makes all the properties with "required" non-optional
        if (additionalProperties.containsKey(RESPONSE_AS)) {
            Object responseAsObject = additionalProperties.get(RESPONSE_AS);
            if (responseAsObject instanceof String) {
                setResponseAs(((String) responseAsObject).split(","));
            } else {
                setResponseAs((String[]) responseAsObject);
            }
        }
        additionalProperties.put(RESPONSE_AS, responseAs);
        if (ArrayUtils.contains(responseAs, LIBRARY_PROMISE_KIT)) {
            additionalProperties.put("usePromiseKit", true);
        }
        if (ArrayUtils.contains(responseAs, LIBRARY_RX_SWIFT)) {
            additionalProperties.put("useRxSwift", true);
        }

        supportingFiles.add(new SupportingFile("Podspec.mustache", "", projectName + ".podspec"));
        supportingFiles.add(new SupportingFile("Cartfile.mustache", "", "Cartfile"));
        supportingFiles.add(new SupportingFile("Models.mustache", sourceFolder, "Models.swift"));
        supportingFiles.add(new SupportingFile("APIs.mustache", sourceFolder, "APIs.swift"));
        supportingFiles.add(new SupportingFile("AlamofireImplementations.mustache", sourceFolder, "AlamofireImplementations.swift"));
        supportingFiles.add(new SupportingFile("APIHelper.mustache", sourceFolder, "APIHelper.swift"));
        supportingFiles.add(new SupportingFile("Extensions.mustache", sourceFolder, "Extensions.swift"));
    }

    public void setResponseAs(String[] responseAs) {
        this.responseAs = responseAs;
    }
}
