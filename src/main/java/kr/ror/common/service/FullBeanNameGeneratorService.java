package kr.ror.common.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

public class FullBeanNameGeneratorService implements BeanNameGenerator {
    
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     *  이 클래스는 Spring이 기본적으로 사용하는 BeanNameGenerator. 컨트롤러가 아닐경우 이 클래스를 사용하려고 선언
     */
    private final AnnotationBeanNameGenerator defaultGenerator = new AnnotationBeanNameGenerator();
    
    @Override
    public String generateBeanName(final BeanDefinition definition, final BeanDefinitionRegistry registry) {    
        final String result;
        
        // definition이 컨트롤러일 경우 패키지 이름을 포함한 Bean 이름을, 아닐경우 Spring 기본형식을 따름
        /*
        if (isController(definition)) {
            result = generateFullBeanName((AnnotatedBeanDefinition) definition);
        } else {
            result = this.defaultGenerator.generateBeanName(definition, registry);
        }
        */
        result = generateFullBeanName((AnnotatedBeanDefinition) definition);
        
        return result;
    }

    private String generateFullBeanName(final AnnotatedBeanDefinition definition) {
        // 패키지를 포함한 전체 이름을 반환
        return definition.getMetadata().getClassName();
    }

    private Set<String> getAnnotationTypes(final BeanDefinition definition) {
        final AnnotatedBeanDefinition annotatedDef = (AnnotatedBeanDefinition) definition;
        final AnnotationMetadata metadata = annotatedDef.getMetadata();
        return metadata.getAnnotationTypes();
    }

    /*
     * Controller인지 판별하는 메서드
     */
    private boolean isController(final BeanDefinition definition) {
        if (definition instanceof AnnotatedBeanDefinition) {            
            // definition에 속한 모든 Annotation을 가져옴.
            final Set<String> annotationTypes = getAnnotationTypes(definition);
            
            // annotation 중 @Controller이거나 @RestController 일 경우 Controller로 인식
            for (final String annotationType : annotationTypes) {
                if (annotationType.equals(Controller.class.getName())) {
                    return true;
                }
                if (annotationType.equals(RestController.class.getName())) {
                    return true;
                }
            }

        }
        return false;
    }
}