package com.ffr.booklibrary.circulation;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import org.junit.jupiter.api.Test;

@AnalyzeClasses(packages = "com.ffr.booklibrary.circulation.core")
public class CirculationArchTest {

  private static final JavaClasses classes =
      new ClassFileImporter().importPackages("com.ffr.booklibrary.circulation.core");

  @Test
  public void onion_architecture_is_respected() {
    onionArchitecture()
        .domainModels("..domain.model..")
        .applicationServices("..application..")
        .adapter("persistence", "..adapters.db..")
        .adapter("api", "..adapters.api..")
        .adapter("event", "..adapters.event..")
        .withOptionalLayers(true)
        .check(classes);
  }
}
