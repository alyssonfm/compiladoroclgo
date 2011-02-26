%%
%standalone

context = context
identifier = [a-zA-Z][a-zA-Z0-9]*

%%

context { System.out.println("Keyword: context"); }
{identifier} { System.out.println("-----id"); }