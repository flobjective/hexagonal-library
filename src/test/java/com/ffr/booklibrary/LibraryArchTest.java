package com.ffr.booklibrary;

import static com.tngtech.archunit.base.DescribedPredicate.doNot;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.ffr.booklibrary")
public class LibraryArchTest {

  public static final String ROOT_PACKAGE = "com.ffr.booklibrary..";
  public static final String SHARED_PACKAGE = "com.ffr.booklibrary.shared..";
  public static final String CIRCULATION_PACKAGE = "com.ffr.booklibrary..circulation..";
  public static final String INVENTORY_PACKAGE = "com.ffr.booklibrary.inventory..";

  @ArchTest
  static final ArchRule circulationDependencies =
      classes()
          .that()
          .resideInAnyPackage(CIRCULATION_PACKAGE)
          .should()
          .onlyDependOnClassesThat(
              resideInAnyPackage(CIRCULATION_PACKAGE, SHARED_PACKAGE)
                  .or(doNot(resideInAPackage(ROOT_PACKAGE))));

  @ArchTest
  static final ArchRule inventoryDependencies =
      classes()
          .that()
          .resideInAnyPackage(INVENTORY_PACKAGE)
          .should()
          .onlyDependOnClassesThat(
              resideInAnyPackage(INVENTORY_PACKAGE, SHARED_PACKAGE)
                  .or(doNot(resideInAPackage(ROOT_PACKAGE))));
}
