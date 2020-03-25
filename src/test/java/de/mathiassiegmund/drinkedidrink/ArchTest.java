package de.mathiassiegmund.drinkedidrink;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("de.mathiassiegmund.drinkedidrink");

        noClasses()
            .that()
            .resideInAnyPackage("de.mathiassiegmund.drinkedidrink.service..")
            .or()
            .resideInAnyPackage("de.mathiassiegmund.drinkedidrink.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..de.mathiassiegmund.drinkedidrink.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
