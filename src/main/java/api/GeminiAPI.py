from dotenv import load_dotenv
import google.generativeai as genai
import os
import sys

# Load environment variables from .env file
load_dotenv()

# Configure API key from environment variables
genai.configure(api_key=os.getenv("MY_API_KEY"))

# Get the input string from command line arguments
input_string = sys.argv[1]

# Initialize a GenerativeModel instance
model = genai.GenerativeModel('gemini-1.5-pro-latest')

# Generate content using the model with the input string
response = model.generate_content(input_string)

# Print the generated response
print(response.text)
