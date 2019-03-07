# Things need to be done #

1. Add scala lang dependency
```
<dependency>
    <groupId>org.scala-lang</groupId>
    <artifactId>scala-library</artifactId>
    <version>2.12.8</version>
</dependency>
```
2. Add scala compiler plugin
```
<plugin>
    <groupId>net.alchim31.maven</groupId>
    <artifactId>scala-maven-plugin</artifactId>
    <version>3.2.2</version>
    <executions>
        <execution>
            <goals>
                <goal>compile</goal>
                <goal>testCompile</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
3. Instruct maven bundle plugin to bundle scala related packages
```
<plugin>
    <groupId>org.apache.felix</groupId>
    <artifactId>maven-bundle-plugin</artifactId>
    <version>3.5.0</version>
    <extensions>true</extensions>
    <configuration>
        <instructions>
            <Private-Package>scala.*</Private-Package>
            <Import-Package>!org.apache.felix.scr.annotations, *</Import-Package>
        </instructions>
    </configuration>
</plugin>
```
4. Tell scr plugin to generate infos for scala
```
<plugin>
    <groupId>org.apache.felix</groupId>
    <artifactId>maven-scr-plugin</artifactId>
    <version>1.26.0</version>
    <configuration>
        <supportedProjectTypes>
            <supportedProjectType>bundle</supportedProjectType>
            <supportedProjectType>war</supportedProjectType>
        </supportedProjectTypes>
        <scanClasses>true</scanClasses>
    </configuration>
    <executions>
        <execution>
            <id>generate-scr-scrdescriptor</id>
            <goals>
                <goal>scr</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```