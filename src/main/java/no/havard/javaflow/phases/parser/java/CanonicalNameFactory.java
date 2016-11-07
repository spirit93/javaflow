package no.havard.javaflow.phases.parser.java;

import static no.havard.javaflow.model.CanonicalName.object;
import static no.havard.javaflow.util.Maps.entriesToMap;
import static no.havard.javaflow.util.Maps.entry;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import no.havard.javaflow.model.CanonicalName;

public class CanonicalNameFactory {

  private static Map<String, String> BUILTIN = Collections.unmodifiableMap(Stream.of(
      entry("Boolean", "java.lang"),
      entry("Byte", "java.lang"),
      entry("Character", "java.lang"),
      entry("Double", "java.lang"),
      entry("Float", "java.lang"),
      entry("Integer", "java.lang"),
      entry("Long", "java.lang"),
      entry("Short", "java.lang"),
      entry("String", "java.lang"),

      entry("List", "java.util"),
      entry("Map", "java.util")
  ).collect(entriesToMap()));

  private final String packageName;
  private final Map<String, String> imports;

  public CanonicalNameFactory(String packageName, Map<String, String> imports) {
    this.packageName = packageName;
    this.imports = imports;
  }

  CanonicalName build(String name) {
    String packageName = imports.getOrDefault(name, BUILTIN.getOrDefault(name, this.packageName));
    return object(packageName, name);
  }

}

