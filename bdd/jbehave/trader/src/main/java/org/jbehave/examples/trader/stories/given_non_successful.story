Scenario: A scenario that depends on a non successful story

GivenStories: org/jbehave/examples/trader/stories/non_successful.story

Given the traders:
|name |rank    |
|Larry|Stooge 3|
|Moe  |Stooge 1|
|Curly|Stooge 2|
When Traders subset to ".*e" by name
Then the traders returned are:
|name|rank    |
|Moe |Stooge 1|
