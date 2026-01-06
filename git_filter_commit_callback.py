
def commit_callback(commit):
    # Set every commit message to 'Initial commit' (with trailing newline)
    commit.message = b"Initial commit\n"
