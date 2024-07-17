from dotenv import load_dotenv
import google.generativeai as genai
import os

# Load environment variables from .env file
load_dotenv()

# Configure API key from environment variables
genai.configure(api_key=os.getenv("MY_API_KEY"))

# Initialize a GenerativeModel instance
model = genai.GenerativeModel('gemini-1.5-pro-latest')

# Generate content using the model
response = model.generate_content("What is the meaning of life in two sentences")

# Print the generated response
print(response.text)