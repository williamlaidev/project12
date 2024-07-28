import google.generativeai as genai  # noqa: F401
import os  # noqa: F401
import sys  # noqa: F401
from dotenv import load_dotenv  # noqa: F401

# Load environment variables from .env file
load_dotenv()

# Configure API key from environment variables
genai.configure(api_key=os.getenv("GEMINI_API_KEY"))

# Get the input string from command line arguments
input_string = sys.argv[1]

# Initialize a GenerativeModel instance
model = genai.GenerativeModel('gemini-1.5-pro-latest')

# Generate content using the model with the input string
response = model.generate_content(input_string)

# Print the generated response
print(response.text)
