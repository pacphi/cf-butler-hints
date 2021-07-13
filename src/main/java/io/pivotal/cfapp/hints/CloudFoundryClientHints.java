package io.pivotal.cfapp.hints;

import static org.springframework.nativex.hint.AccessBits.ALL;

import java.util.List;

import org.springframework.nativex.type.AccessDescriptor;
import org.springframework.nativex.type.HintDeclaration;
import org.springframework.nativex.type.NativeConfiguration;
import org.springframework.nativex.type.TypeProcessor;
import org.springframework.nativex.type.TypeSystem;



public class CloudFoundryClientHints implements NativeConfiguration {
	
	@Override
	public List<HintDeclaration> computeHints(TypeSystem typeSystem) {
		return TypeProcessor
				.namedProcessor("cloudfoundry.client - processor")
				.skipAnnotationInspection()
				.skipConstructorInspection()
				.onTypeDiscovered((type, context) -> context.addReflectiveAccess(type, new AccessDescriptor(ALL)))
					.use(typeSystem)
						.toProcessTypes(
							typeSystem
								.scanDependencies("cloudfoundry-client*")
								.forTypes(type -> {
									return 
										typeSystem.resolveDotted("org.cloudfoundry.client.v2.PaginatedResponse").isAssignableFrom(type) ||
										typeSystem.resolveDotted("org.cloudfoundry.client.v3.PaginatedResponse").isAssignableFrom(type) ||
										typeSystem.resolveDotted("org.cloudfoundry.client.v2.Resource").isAssignableFrom(type) ||
										typeSystem.resolveDotted("org.cloudfoundry.client.v3.Resource").isAssignableFrom(type);
								})
								.stream()
						);
	}
}