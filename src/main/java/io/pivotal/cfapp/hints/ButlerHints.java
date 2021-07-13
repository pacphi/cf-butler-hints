package io.pivotal.cfapp.hints;

import static org.springframework.nativex.hint.ProxyBits.IS_STATIC;

import java.util.Collections;
import java.util.List;

import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.type.HintDeclaration;
import org.springframework.nativex.type.NativeConfiguration;
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
public class ButlerHints implements NativeConfiguration {
	
	@Override
	public List<HintDeclaration> computeHints(TypeSystem typeSystem) {
		return Collections.emptyList();
	}
}