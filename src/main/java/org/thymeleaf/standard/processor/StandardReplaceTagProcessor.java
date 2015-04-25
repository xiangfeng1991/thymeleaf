/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.standard.processor;

import org.thymeleaf.context.ITemplateProcessingContext;
import org.thymeleaf.engine.IElementStructureHandler;
import org.thymeleaf.engine.TemplateHandlerEventQueue;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IOpenElementTag;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeMatchingHTMLElementTagProcessor;

/**
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.0
 *
 */
public final class StandardReplaceTagProcessor extends AbstractAttributeMatchingHTMLElementTagProcessor {

    public static final int PRECEDENCE = 100;
    public static final String ATTR_NAME = "replace";

    public StandardReplaceTagProcessor() {
        super(ATTR_NAME, PRECEDENCE);
    }



    public void process(
            final ITemplateProcessingContext processingContext,
            final IProcessableElementTag tag,
            final IElementStructureHandler structureHandler) {

        final IModelFactory modelFactory = processingContext.getModelFactory();

        final TemplateHandlerEventQueue queue = new TemplateHandlerEventQueue();

        final IOpenElementTag sectionOpenTag = modelFactory.createOpenElementTag("section");
        sectionOpenTag.getAttributes().setAttribute("class", "included");

        queue.add(sectionOpenTag);
        queue.add(modelFactory.createText("This is replaced text!"));
        queue.add(modelFactory.createCloseElementTag("section"));

        structureHandler.replaceWith(queue, true);

    }


}
