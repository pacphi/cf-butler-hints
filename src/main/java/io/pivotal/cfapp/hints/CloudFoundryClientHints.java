package io.pivotal.cfapp.hints;

import static org.springframework.nativex.hint.AccessBits.ALL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.nativex.type.AccessDescriptor;
import org.springframework.nativex.type.HintDeclaration;
import org.springframework.nativex.type.NativeConfiguration;
import org.springframework.nativex.type.Type;
import org.springframework.nativex.type.TypeProcessor;
import org.springframework.nativex.type.TypeSystem;



public class CloudFoundryClientHints implements NativeConfiguration {
	
	@Override
	public List<HintDeclaration> computeHints(TypeSystem typeSystem) {
		Set<String> responseTypes = 
			Set.of(
				"org.cloudfoundry.client.v2.PaginatedResponse",
				"org.cloudfoundry.client.v3.PaginatedResponse",
				"org.cloudfoundry.client.v2.Resource",
				"org.cloudfoundry.client.v3.Resource"
			);

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
									return responseTypes.stream().anyMatch(t -> typeSystem.resolveDotted(t).isAssignableFrom(type));
								})
								.stream()
						);
	}
}