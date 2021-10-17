package com.ffr.booklibrary.inventory;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "com.ffr.booklibrary.inventory.core")
public class InventoryArchTest {

    private static final JavaClasses classes = new ClassFileImporter()
            .importPackages("com.ffr.booklibrary.inventory.core");

    @Test
    public void onion_architecture_is_respected() {
        onionArchitecture()
                .domainModels("..domain.model..")
                .applicationServices("..application..")
                .adapter("persistence", "..adapters.db..")
                .adapter("api", "..adapters.api..")
                .adapter("event", "..adapters.event..")
                .adapter("openlibrary", "..adapters.openlibrary..")
                .withOptionalLayers(true)
                .check(classes);
    }
}
