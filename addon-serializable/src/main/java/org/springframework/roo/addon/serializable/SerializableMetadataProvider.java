package org.springframework.roo.addon.serializable;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.springframework.roo.addon.beaninfo.BeanInfoMetadata;
import org.springframework.roo.addon.beaninfo.BeanInfoMetadataProvider;
import org.springframework.roo.classpath.PhysicalTypeIdentifier;
import org.springframework.roo.classpath.PhysicalTypeMetadata;
import org.springframework.roo.classpath.itd.AbstractItdMetadataProvider;
import org.springframework.roo.classpath.itd.ItdTypeDetailsProvidingMetadataItem;
import org.springframework.roo.model.JavaType;
import org.springframework.roo.project.Path;

/**
 * Provides {@link SerializableMetadata}.
 * 
 * @author Alan Stewart
 * @since 1.1
 */
@Component
@Service
public final class SerializableMetadataProvider extends AbstractItdMetadataProvider {

	@Reference private BeanInfoMetadataProvider beanInfoMetadataProvider;
	
	protected void activate(ComponentContext context) {
		metadataDependencyRegistry.registerDependency(PhysicalTypeIdentifier.getMetadataIdentiferType(), getProvidesType());
		beanInfoMetadataProvider.addMetadataTrigger(new JavaType(RooSerializable.class.getName()));
		addMetadataTrigger(new JavaType(RooSerializable.class.getName()));
	}

	protected void deactivate(ComponentContext context) {
		beanInfoMetadataProvider.removeMetadataTrigger(new JavaType(RooSerializable.class.getName()));
	}
	
	protected ItdTypeDetailsProvidingMetadataItem getMetadata(String metadataIdentificationString, JavaType aspectName, PhysicalTypeMetadata governorPhysicalTypeMetadata, String itdFilename) {
		// Acquire bean info (we need getters details, specifically)
		JavaType javaType = SerializableMetadata.getJavaType(metadataIdentificationString);
		Path path = SerializableMetadata.getPath(metadataIdentificationString);
		String beanInfoMetadataKey = BeanInfoMetadata.createIdentifier(javaType, path);

		// We need to lookup the metadata we depend on
		BeanInfoMetadata beanInfoMetadata = (BeanInfoMetadata) metadataService.get(beanInfoMetadataKey);

		// Abort if we don't have getter information available
		if (beanInfoMetadata == null || !beanInfoMetadata.isValid()) {
			return null;
		}

		// We need to be informed if our dependent metadata changes
		metadataDependencyRegistry.registerDependency(beanInfoMetadataKey, metadataIdentificationString);

		// Otherwise go off and create the to String metadata
		return new SerializableMetadata(metadataIdentificationString, aspectName, governorPhysicalTypeMetadata);
	}

	public String getItdUniquenessFilenameSuffix() {
		return "Serializable";
	}

	protected String getGovernorPhysicalTypeIdentifier(String metadataIdentificationString) {
		JavaType javaType = SerializableMetadata.getJavaType(metadataIdentificationString);
		Path path = SerializableMetadata.getPath(metadataIdentificationString);
		return PhysicalTypeIdentifier.createIdentifier(javaType, path);
	}

	protected String createLocalIdentifier(JavaType javaType, Path path) {
		return SerializableMetadata.createIdentifier(javaType, path);
	}

	public String getProvidesType() {
		return SerializableMetadata.getMetadataIdentiferType();
	}
}
