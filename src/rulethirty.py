gen = int(raw_input("Enter number of generations:"))
seeds = raw_input("Enter input seeds:") 

rule = {'111':'0','110':'0','101':'0','100':'1','011':'1','010':'1','001':'1','000':'0'}
matrix = [seeds]

line = ""
tmp = seeds

def extend(tmp, line):
	newline = rule.get(tmp[-1:]+tmp[:2]) + line + rule.get(tmp[-2:]+tmp[:1])
	return newline

for x in range(0,gen):
	for i in range(1,(len(tmp)-1)): 
		line += rule.get(tmp[i-1:(i+1)+1])
	
	line = extend(tmp, line)	
	matrix += [line]
	tmp = line
	line = ""

for i in matrix:
	print i