package test.ricardo.rpsgame.arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AnalyzeClasses(packages = "test.ricardo.rpsgame", importOptions = { ImportOption.DoNotIncludeTests.class })
class ArchUnitTest {

	private static final String DOMAIN_PACKAGE = "..domain..";
	private static final String APPLICATION_PACKAGE = "..application..";
	private static final String ADAPTERS_PACKAGE = "..adapters..";
	private static final String CONFIG_PACKAGE = "..config..";

	/*
	 * General
	 */
	@ArchTest
	public void packagesShouldBeFreeOfCycles(JavaClasses importedClasses) {
		ArchRule myRule = slices().matching("test.ricardo.rpsgame.(*)..")
				.should()
				.beFreeOfCycles();
		myRule.check(importedClasses);
	}

	@ArchTest
	public void domainShouldNotDependOnAdapters(JavaClasses importedClasses) {
		checkNoClassInPackageShouldDependOnAnother(importedClasses, DOMAIN_PACKAGE, ADAPTERS_PACKAGE);
	}

	@ArchTest
	public void domainShouldNotDependOnApplication(JavaClasses importedClasses) {
		checkNoClassInPackageShouldDependOnAnother(importedClasses, DOMAIN_PACKAGE, APPLICATION_PACKAGE);
	}

	@ArchTest
	public void domainShouldNotDependOnConfig(JavaClasses importedClasses) {
		checkNoClassInPackageShouldDependOnAnother(importedClasses, DOMAIN_PACKAGE, CONFIG_PACKAGE);
	}

	@ArchTest
	public void applicationShouldNotDependOnAdapters(JavaClasses importedClasses) {
		checkNoClassInPackageShouldDependOnAnother(importedClasses, APPLICATION_PACKAGE, ADAPTERS_PACKAGE);
	}

	@ArchTest
	public void applicationShouldNotDependOnConfig(JavaClasses importedClasses) {
		checkNoClassInPackageShouldDependOnAnother(importedClasses, APPLICATION_PACKAGE, CONFIG_PACKAGE);
	}

	@ArchTest
	public void adaptersShouldNotDependOnConfig(JavaClasses importedClasses) {
		checkNoClassInPackageShouldDependOnAnother(importedClasses, ADAPTERS_PACKAGE, CONFIG_PACKAGE);
	}

	public static void checkNoClassInPackageShouldDependOnAnother(JavaClasses importedClasses, String packageToCheck,
			String packageToNotDependOn) {
		log.info("Class in package {} should not depend on classes in package {}", packageToCheck,
				packageToNotDependOn);
		ArchRule myRule = noClasses().that()
				.resideInAPackage(packageToCheck)
				.should()
				.dependOnClassesThat()
				.resideInAPackage(packageToNotDependOn);
		myRule.check(importedClasses);
	}

}
