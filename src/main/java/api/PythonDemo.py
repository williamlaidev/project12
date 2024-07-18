# This Python file is used by JythonDemo to test that Jython can run Python code
# and import basic classes.

import math

def calculate_square_root(num):
    return math.sqrt(num)

num = 25
result = calculate_square_root(num)
print("Square root of {0} is: {1}".format(num, result))
