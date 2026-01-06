import sys

def rewrite_stream(infile, outfile):
    in_commit = False
    saw_committer = False
    while True:
        line = infile.readline()
        if not line:
            break
        outfile.write(line)
        if line.startswith(b'commit '):
            in_commit = True
            saw_committer = False
            continue
        if in_commit and line.startswith(b'committer '):
            saw_committer = True
            continue
        if in_commit and saw_committer and line.startswith(b'data '):
            size = int(line.split()[1])
            msg = infile.read(size)
            newmsg = b'Initial commit\n'
            outfile.write(b'data ' + str(len(newmsg)).encode() + b"\n")
            outfile.write(newmsg)
            in_commit = False
            saw_committer = False
            continue

if __name__ == '__main__':
    with open('repo.export', 'rb') as fin, open('repo.modified', 'wb') as fout:
        rewrite_stream(fin, fout)
