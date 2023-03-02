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
package de.friday.test.support.rules.dsl.specification;

import de.friday.sonarqube.gosu.plugin.rules.BaseGosuRule;

/**
 * Defines the basic specification for a Rule test.
 *
 */
public interface RuleSpecification extends RuleRunner {

    /**
     *
     * Specify the source code file to be analysed by the Rule.
     * <p>
     * E.g.
     * <pre>
     * given(new SourceCodeFile("TODOsRule/ok.gs")).whenCheckedAgainst(TODOsRule.class).then().issuesFound().areEmpty();
     * </pre>
     * This runs the TODOsRule against the ok.gs source code file and return the list of issues found.
     * </p>
     * @param sourceCodeFile Source code file to run the check against
     * @return The Rule specification
     */
    RuleSpecification given(SourceCodeFile sourceCodeFile);

    /**
     *
     * Specify the source code file to be analysed by the Check.
     * <p>
     * E.g.
     * <pre>
     * given("TODOsRule/nok.gs").whenCheckedAgainst(TODOsRule.class).then().issuesFound().hasSizeEqualTo(5);
     * </pre>
     * This runs the TODOsRule against the nok.gs source code file and return the list of issues found.
     * </p>
     * @param sourceCodeFileName Source code file name to run the check against
     * @return The Check specification
     */
    RuleSpecification given(String sourceCodeFileName);

    /**
     *
     * Specify the Check that it be used to run the analyses on the source code file.
     * <p>
     * E.g.
     * <pre>
     * given("TODOsRule/ok.gs").whenCheckedAgainst(TODOsRule.class).then().issuesFound().areEmpty();
     * </pre>
     * This runs the TODOsRule against the ok.gs source code file and return the list of issues found.
     * </p>
     * @param checkClass Source code file to run the check against
     * @return The Check specification
     */
    RuleSpecification whenCheckedAgainst(Class<? extends BaseGosuRule> checkClass);

    /**
     * Returns the issue specification so that you can set up the expectations on the issues returned by the Check.
     * E.g.
     * <pre>
     * given("TODOsRule/ok.gs").whenCheckedAgainst(TODOsRule.class).then().issuesFound().areEmpty();g
     * </pre>
     *
     * @return The issue specificaiton
     */
    IssueSpecification then();

}
