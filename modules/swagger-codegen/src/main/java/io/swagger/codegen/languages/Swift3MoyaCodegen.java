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

public class Swift3MoyaCodegen extends AbstractSwift3Codegen {

    protected String[] responseAs = new String[0];

    @Override
    public String getName() {
        return "swift3-moya";
    }

    public Swift3MoyaCodegen() {
        super();
        embeddedTemplateDir = templateDir = "swift3/libraries/moya";
        typeMapping.put("file", "Data");

    }

    @Override
    public void processOpts() {
        super.processOpts();

        supportingFiles.add(new SupportingFile("Podspec.mustache", "", projectName + ".podspec"));
        supportingFiles.add(new SupportingFile("Cartfile.mustache", "", "Cartfile"));
        supportingFiles.add(new SupportingFile("Models.mustache", sourceFolder, "Models.swift"));
        supportingFiles.add(new SupportingFile("APIs.mustache", sourceFolder, "API.swift"));
        supportingFiles.add(new SupportingFile("CompositeEncoding.mustache", sourceFolder, "CompositeEncoding.swift"));
        supportingFiles.add(new SupportingFile("Data+MimeType.mustache", sourceFolder, "Data+MimeType.swift"));
    }

    @Override
    public String toApiName(String name) {
        if (name.length() == 0)
            return "APITargets";
        return initialCaps(name) + "Targets";
    }
}
