/*
 * Copyright (C) 2023 FRIDAY Insurance S.A.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package de.friday.test.support.checks.dsl.gosu;

import de.friday.test.support.checks.dsl.specification.SourceCodeFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;

public final class GosuSourceCodeFile implements SourceCodeFile {
    private final String fileName;

    public GosuSourceCodeFile(String filename) {
        this.fileName = filename;
    }

    @Override
    public InputFile asInputFile() {
        final String sourceFileContent = loadFileContent();
        return new TestInputFileBuilder(GosuCheckTestResources.getBaseDirPathAsString(), fileName).initMetadata(sourceFileContent).build();
    }

    private String loadFileContent() {
        final Path gosuFilePath = GosuCheckTestResources.getPathOf(fileName);

        try (Stream<String> lines = Files.lines(gosuFilePath)) {
            return lines.collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Unable to load Gosu source file at: " + gosuFilePath);
        }
    }
}
