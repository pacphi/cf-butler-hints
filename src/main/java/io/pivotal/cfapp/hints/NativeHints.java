package io.pivotal.cfapp.hints;

import static org.springframework.nativex.hint.AccessBits.ALL;
import static org.springframework.nativex.hint.ProxyBits.IS_STATIC;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.type.AccessDescriptor;
import org.springframework.nativex.type.HintDeclaration;
import org.springframework.nativex.type.NativeConfiguration;
import org.springframework.nativex.type.Type;
import org.springframework.nativex.type.TypeProcessor;
import org.springframework.nativex.type.TypeSystem;


@NativeHint(
	aotProxies = {
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcAppDetailRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcAppMetricsRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcAppRelationshipRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcHistoricalRecordRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcOrganizationRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcPoliciesRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcQueryRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcServiceInstanceDetailRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcServiceInstanceMetricsRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcSpaceRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcSpaceUsersRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.repository.R2dbcTimeKeeperRepository", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.DemographicsService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.DormantWorkloadsService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.EventsService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.LegacyWorkloadsService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.ResourceMetadataService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.SnapshotService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.UsageService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.UserSpacesService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.ProductMetricsService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.AppDetailService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.AppMetricsService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.AppRelationshipService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.HistoricalRecordService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.OrganizationService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.PoliciesService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.QueryService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.ServiceInstanceDetailService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.ServiceInstanceMetricsService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.SpaceService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.SpaceUsersService", proxyFeatures = IS_STATIC),
		@AotProxyHint(targetClassName = "io.pivotal.cfapp.service.TimeKeeperService", proxyFeatures = IS_STATIC)
	}
)
public class NativeHints implements NativeConfiguration {

	private static Logger log = LoggerFactory.getLogger(NativeHints.class);
	
	@Override
	public List<HintDeclaration> computeHints(TypeSystem typeSystem) {
		List<Type> candidates = typeSystem.scan(type -> {
			return type.isPartOfDomain("org.cloudfoundry.client") && 
				(
					typeSystem.resolveDotted("org.cloudfoundry.client.v2.PaginatedResponse").isAssignableFrom(type) ||
					typeSystem.resolveDotted("org.cloudfoundry.client.v3.PaginatedResponse").isAssignableFrom(type) ||
					typeSystem.resolveDotted("org.cloudfoundry.client.v2.Resource").isAssignableFrom(type) ||
					typeSystem.resolveDotted("org.cloudfoundry.client.v3.Resource").isAssignableFrom(type)
				);	
		});
		dumpHints(candidates);
		return TypeProcessor
				.namedProcessor("cloudfoundry.client - processor")
				.skipAnnotationInspection()
				.skipConstructorInspection()
				.onTypeDiscovered((type, context) -> context.addReflectiveAccess(type, new AccessDescriptor(ALL)))
					.use(typeSystem)
						.toProcessTypes(candidates);
	}

	private void dumpHints(List<Type> candidates) {
		String filename = "/tmp/cf-butler-hints." + LocalDateTime.now().toString() + ".out";
		String content = candidates.stream().map(t -> t.getDottedName()).collect(Collectors.joining("\n"));
		try {
			File file = new File(filename);
			FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
		} catch (IOException ioe) {
			log.warn("Couldn't dump {} to /tmp!", filename);
		}
	}
}